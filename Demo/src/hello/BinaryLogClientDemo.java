package hello;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventHeaderV4;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

public class BinaryLogClientDemo {

    public static void printArr(Object[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length - 1] + "]\n");
    }

    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 3306;
        String userName = "root";
        String password = "5555";
        Map<Long, String> table = new HashMap<>();

        BinaryLogClient client = new BinaryLogClient(hostName, port, userName, password);

        client.registerEventListener(event -> {
            EventData data = event.getData();
            EventHeaderV4 headerV4 = event.getHeader();
            String database = null;
            Serializable[] series;
            switch (headerV4.getEventType()) {
                case EXT_WRITE_ROWS:
                    WriteRowsEventData queryEventData = (WriteRowsEventData) data;
                    series = queryEventData.getRows().get(0);
                    database = table.get(queryEventData.getTableId());
                    if (database != null) {
                        System.out.print("Insert table " + database + ": ");
                        printArr(series);
                    }
                    break;
                case EXT_UPDATE_ROWS:
                    UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) data;
                    series = updateRowsEventData.getRows().get(0).getValue();
                    database = table.get(updateRowsEventData.getTableId());
                    if (database != null) {
                        System.out.print("Update table " + database + ": ");
                        printArr(series);
                    }
                    break;
                case EXT_DELETE_ROWS:
                    DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) data;
                    series = deleteRowsEventData.getRows().get(0);
                    database = table.get(deleteRowsEventData.getTableId());
                    if (database != null) {
                        System.out.print("Delete table " + database + ": ");
                        printArr(series);
                    }
                    break;
                case TABLE_MAP:
                    TableMapEventData tableMapEventData = (TableMapEventData) data;
                    if (table.get(tableMapEventData.getTableId()) == null)
                        table.put(tableMapEventData.getTableId(), tableMapEventData.getDatabase()
                                + "." + tableMapEventData.getTable());
                    break;
                case QUERY:
                    // QueryEventData eventData = (QueryEventData) data;
                    // System.out.println(eventData.getSql());
                    break;
                default:
                    break;
            }
        });

        Thread t = new Thread(() -> {
            try {
                client.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t.start();
        System.out.println("Listening change events...");

    }

}

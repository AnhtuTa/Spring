package hello.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tạo ra ThreadPool sử dụng phương thức newCachedThreadPool() vì vậy kích thước của ThreadPool là
 * gần như không giới hạn (Integer.MAX_VALUE), nên nó sẽ bắt đầu thực thi chương trình trên với 1
 * task và các task khác sẽ ở không phải đợi. Nếu có Thread rảnh thì nó sẽ nhận task và thực thi.
 * Nếu không có Thread rảnh thì nó sẽ tạo một Thread mới và thực thi.
 */
public class CachedThreadPoolExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 1; i <= 100; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
            Thread.sleep(20);
        }

        /*
         * Initiates an orderly shutdown in which previously submitted tasks are executed, but no
         * new tasks will be accepted. Invocation has no additional effect if already shut down.
         * This method does not wait for previously submitted tasks to complete execution. Use
         * awaitTermination to do that.
         */
        executor.shutdown();

        // Wait until all threads are finish
        while (!executor.isTerminated()) {
            // Running ...
        }

        System.out.println("Finished all threads");
    }
}

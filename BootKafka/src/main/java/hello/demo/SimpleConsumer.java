package hello.demo;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // Group Id của consumer được gen random
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        // Để xác định vị trí đọc message (offset) ta sử dụng config
        // ConsumerConfig.AUTO_OFFSET_RESET_CONFIG với 3 tùy chọn
        // earliest(đọc cả các message có trên topic trước đó)
        // latest đọc các message mới nhất từ khi consumer subcribe
        // none
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // Đối tượng consumer lắng nghe (subscribe) topic có name là "event1":
        // consumer.subscribe(Arrays.asList("test")); (Một consumer có thể lắng nghe nhiều topic
        // bằng cách truyền một mảng topic name vào method subscribe()
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("event1"));

        while (true) {
            @SuppressWarnings("deprecation")
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(),
                        record.key(), record.value());
                if(record.value().equals("exit")) {
                    consumer.close();
                }
            }
        }
    }
}

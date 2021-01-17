package hello.listener;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/*
 * Class StartupListener thực hiện implements ApplicationRunner nên nó sẽ được chạy hàm run ngay khi
 * start project
 */
@Component
public class StartupListener implements ApplicationRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("topic1", msg);
    }

    @KafkaListener(topics = "topic1", groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received Message in group - group-id: " + message);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            sendMessage("Now: " + new Date());
            Thread.sleep(2000);
        }
    }
}

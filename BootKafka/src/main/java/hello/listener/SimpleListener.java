package hello.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {

    @KafkaListener(topics = "topic1", groupId = "05-11-1995")
    public void listen(String message) {
        System.out.println("Received Message in group - 05-11-1995: " + message);
    }

}

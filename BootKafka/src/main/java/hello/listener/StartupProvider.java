package hello.listener;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/*
 * Class StartupProvider thực hiện implements ApplicationRunner nên nó sẽ được chạy hàm run ngay khi
 * start project. Khi start project, Provider này sẽ được tạo và liên tục send message tới topic1
 */
@Component
public class StartupProvider implements ApplicationRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send("topic1", "Now: " + new Date());
            Thread.sleep(2000);
        }
    }
}

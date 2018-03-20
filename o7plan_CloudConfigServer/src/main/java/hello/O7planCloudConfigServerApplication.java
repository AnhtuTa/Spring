package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer  // ==> Important!!
@SpringBootApplication
public class O7planCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(O7planCloudConfigServerApplication.class, args);
	}
}

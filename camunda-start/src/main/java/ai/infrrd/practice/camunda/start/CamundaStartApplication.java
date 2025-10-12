package ai.infrrd.practice.camunda.start;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = {"classpath:processes/*.bpmn", "classpath:processes/*.form"})
public class CamundaStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaStartApplication.class, args);
	}

}

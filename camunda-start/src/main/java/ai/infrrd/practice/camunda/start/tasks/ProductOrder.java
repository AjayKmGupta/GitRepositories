package ai.infrrd.practice.camunda.start.tasks;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductOrder {


    @JobWorker(type = "product-order", autoComplete = true)
    public Map<String, Object> getProudct() {
        System.out.println("Product Order Task Executed");
        Map<String, Object> response = Map.of(
                "productId", "12345",
                "productName", "Sample Product",
                "quantity", 10,
                "price", 99.99
        );
        return response;
    }

}

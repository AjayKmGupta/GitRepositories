package ai.infrrd.practice.camunda.start.tasks;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailsPrint {

    @JobWorker(type = "print-product-id", autoComplete = true)
    public void printVariable(final ActivatedJob job) {
        System.out.println("Product ID: " + job.getVariablesAsMap().get("productId"));
    }

}

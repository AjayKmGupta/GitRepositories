package engineering.everest.courier.app.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeliveryPackage {

    String id;
    int weight;
    int distance;
    String offerCode;

    double discount;
    double totalCost;
    double deliveryTime; // in hours

}

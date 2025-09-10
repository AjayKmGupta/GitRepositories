package engineering.everest.courier.app.entity;

import lombok.Data;

@Data
public class DeliveryPackage {

    String id;
    int weight;
    int distance;
    String offerCode;

    double discount;
    double totalCost;
    double deliveryTime; // in hours

    public DeliveryPackage(String id, int weight, int distance, String offerCode) {
        this.id = id;
        this.weight = weight;
        this.distance = distance;
        this.offerCode = offerCode;
    }

}

package engineering.everest.courier.app.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {

    String code;
    double discountRate; // e.g. 0.10 for 10%
    int minWeight;
    int maxWeight;
    int minDistance;
    int maxDistance;

    public boolean isApplicable(DeliveryPackage pkg) {
        return pkg.weight >= minWeight && pkg.weight <= maxWeight && pkg.distance >= minDistance && pkg.distance <= maxDistance;
    }

}

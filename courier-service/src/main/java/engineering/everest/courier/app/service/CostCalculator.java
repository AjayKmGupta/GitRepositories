package engineering.everest.courier.app.service;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.entity.Offer;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class CostCalculator {

    private final int baseDeliveryCost;
    private final Map<String, Offer> offerMap;

    public void calculate(DeliveryPackage pkg) {
        double cost = baseDeliveryCost + pkg.getWeight() * 10 + pkg.getDistance() * 5;
        Offer offer = offerMap.get(pkg.getOfferCode());
        double discount = 0;
        if (offer != null && offer.isApplicable(pkg)) {
            discount = cost * offer.getDiscountRate();
        }
        pkg.setDiscount(Math.round(discount));
        pkg.setTotalCost(Math.round(cost - discount));
    }

}

package engineering.everest.courier.app.service.impl;

import engineering.everest.courier.app.constants.CourierAppConstants;
import engineering.everest.courier.app.entity.DeliveryPackage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Calculates delivery cost and applies applicable discounts for packages.
 * <p>
 * Uses a base delivery cost, package attributes, and an {@link OfferEvaluator} for discount calculation.
 */
@Slf4j
@AllArgsConstructor
public class CostCalculator {

    /**
     * Base fixed delivery cost applicable to all packages.
     */
    private final int baseDeliveryCost;
    /**
     * Offer evaluator used to determine applicable discounts for a package.
     */
    private final OfferEvaluator offerEvaluator;

    /**
     * Calculates the total delivery cost for the given package considering weight,
     * distance, base delivery cost, and applicable discount offers.
     * <p>
     * Updates the package with calculated discount and total cost values.
     *
     * @param pkg the delivery package to calculate cost for
     */
    public void calculate(DeliveryPackage pkg) {
        // Calculate base cost plus variable costs based on weight and distance
        double cost = baseDeliveryCost + pkg.getWeight() * CourierAppConstants.COST_PER_KG + pkg.getDistance() * CourierAppConstants.COST_PER_KM;
        // Evaluate discount based on offers applicable to this package and cost
        double discount = offerEvaluator.evaluateDiscount(pkg, cost);

        // Set computed discount and total cost back on package entity
        pkg.setDiscount(Math.round(discount));
        pkg.setTotalCost(Math.round(cost - discount));
        log.debug("Calculated costs for package {}: baseCost={}, discount={}, totalCost={}",
                pkg.getId(), cost, pkg.getDiscount(), pkg.getTotalCost());
    }

}

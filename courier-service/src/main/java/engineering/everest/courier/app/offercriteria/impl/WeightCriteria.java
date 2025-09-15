package engineering.everest.courier.app.offercriteria.impl;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.offercriteria.OfferCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link OfferCriteria} based on package weight.
 * <p>
 * Validates whether a delivery package's weight falls within a configured inclusive range.
 */
@Slf4j
@AllArgsConstructor
public class WeightCriteria implements OfferCriteria {

    // Minimum inclusive weight for the criteria to be applicable.
    private final int minWeight;
    // Maximum inclusive weight for the criteria to be applicable.
    private final int maxWeight;

    /**
     * Checks if the delivery package's weight is within the configured range.
     *
     * @param deliveryPackage the package to test for applicability
     * @return {@code true} if package weight is between minWeight and maxWeight (inclusive), {@code false} otherwise
     */
    @Override
    public boolean isApplicable(DeliveryPackage deliveryPackage) {
        boolean applicable = deliveryPackage.getWeight() >= minWeight && deliveryPackage.getWeight() <= maxWeight;
        log.debug("WeightCriteria applied on package {}: weight={}, minWeight={}, maxWeight={}, applicable={}",
                deliveryPackage.getId(), deliveryPackage.getWeight(), minWeight, maxWeight, applicable);
        return applicable;
    }
}

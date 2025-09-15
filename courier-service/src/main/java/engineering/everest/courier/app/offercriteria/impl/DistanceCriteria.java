package engineering.everest.courier.app.offercriteria.impl;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.offercriteria.OfferCriteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link OfferCriteria} based on package distance.
 * <p>
 * Validates whether a delivery package's distance falls within a configured inclusive range.
 */
@Slf4j
@AllArgsConstructor
public class DistanceCriteria implements OfferCriteria {

    //Minimum inclusive distance for the criteria to be applicable.
    private final int minDistance;
    // Maximum inclusive distance for the criteria to be applicable.
    private final int maxDistance;

    /**
     * Checks if the delivery package's distance is within the configured range.
     *
     * @param deliveryPackage the package to test for applicability
     * @return {@code true} if package distance is between minDistance and maxDistance (inclusive), {@code false} otherwise
     */
    @Override
    public boolean isApplicable(DeliveryPackage deliveryPackage) {
        boolean applicable = deliveryPackage.getDistance() >= minDistance && deliveryPackage.getDistance() <= maxDistance;
        log.debug("DistanceCriteria applied on package {}: distance={}, minDistance={}, maxDistance={}, applicable={}",
                deliveryPackage.getId(), deliveryPackage.getDistance(), minDistance, maxDistance, applicable);
        return applicable;
    }
}

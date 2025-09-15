package engineering.everest.courier.app.service.impl;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.entity.Offer;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * Evaluates discount for delivery packages based on
 * applicable offers defined in an offer map.
 * <p>
 * Uses the map of offer codes to {@link Offer} details for lookup.
 * For a given package and its calculated price, returns the discount amount.
 */
@AllArgsConstructor
public class OfferEvaluator {

    /**
     * Map of offer codes to {@link Offer} objects providing discount rules and criteria.
     */
    private final Map<String, Offer> offerMap;

    /**
     * Evaluates and returns discount amount for the given delivery package and price.
     * <p>
     * - Retrieves the offer by the package's offer code.
     * - Checks if the offer is applicable to the package using offer's criteria.
     * - Calculates discount as (price * discount rate).å
     * - Returns 0 if no valid or applicable offer is found.
     *
     * @param pkg   the delivery package to evaluate
     * @param price the calculated total price before discount
     * @return discount amount applicable, or 0 if none
     */
    public double evaluateDiscount(DeliveryPackage pkg, double price) {
        // Lookup offer by package's offer code
        Offer offer = offerMap.get(pkg.getOfferCode());
        // Check for null and applicability based on package details
        if (offer != null && offer.isApplicable(pkg)) {
            // Calculate discount based on offer percentage
            return price * offer.getDiscountRate();
        }
        // No applicable discount
        return 0;
    }

}

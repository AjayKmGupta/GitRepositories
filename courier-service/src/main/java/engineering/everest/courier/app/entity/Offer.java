package engineering.everest.courier.app.entity;

import engineering.everest.courier.app.offercriteria.OfferCriteria;
import engineering.everest.courier.app.util.ValidationUtils;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Offer {

    String code;
    double discountRate; // e.g. 0.10 for 10%
    private final List<OfferCriteria> offerCriterias;

    public boolean isApplicable(DeliveryPackage deliveryPackage) {
        return ValidationUtils.isNotEmpty(offerCriterias) &&
                offerCriterias.stream().allMatch(criteria -> criteria.isApplicable(deliveryPackage));
    }

}

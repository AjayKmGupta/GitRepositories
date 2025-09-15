package engineering.everest.courier.app.offercriteria;

import engineering.everest.courier.app.entity.DeliveryPackage;

public interface OfferCriteria {

    boolean isApplicable(DeliveryPackage deliveryPackage);

}

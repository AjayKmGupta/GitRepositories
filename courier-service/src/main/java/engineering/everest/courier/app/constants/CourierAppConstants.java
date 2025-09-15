package engineering.everest.courier.app.constants;

import engineering.everest.courier.app.entity.Offer;
import engineering.everest.courier.app.offercriteria.impl.DistanceCriteria;
import engineering.everest.courier.app.offercriteria.impl.WeightCriteria;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CourierAppConstants {

    public static final double COST_PER_KG = 10;
    public static final double COST_PER_KM = 5;

    public static final Map<String, Offer> OFFER_MAP = Map.of(
            "OFR001", Offer.builder()
                    .code("OFR001")
                    .discountRate(0.10)
                    .offerCriterias(List.of(
                            new WeightCriteria(70, 200),
                            new DistanceCriteria(0, 199)
                    ))
                    .build(),
            "OFR002", Offer.builder()
                    .code("OFR002")
                    .discountRate(0.07)
                    .offerCriterias(List.of(
                            new WeightCriteria(100, 250),
                            new DistanceCriteria(50, 150)
                    ))
                    .build(),
            "OFR003", Offer.builder()
                    .code("OFR003")
                    .discountRate(0.05)
                    .offerCriterias(List.of(
                            new WeightCriteria(10, 150),
                            new DistanceCriteria(50, 250)
                    ))
                    .build()
    );

}

package engineering.everest.courier.app.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vehicle {

    int maxSpeed;
    int maxWeight;
    double nextAvailable;

}

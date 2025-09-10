package engineering.everest.courier.app.entity;

import lombok.Data;

@Data
public class Vehicle {

    int maxSpeed;
    int maxWeight;
    double nextAvailable;

    public Vehicle(int maxSpeed, int maxWeight) {
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
        this.nextAvailable = 0;
    }
}

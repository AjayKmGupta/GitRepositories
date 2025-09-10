package engineering.everest.courier.app.service;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.entity.Vehicle;

import java.util.*;

public class DeliveryScheduler {

    public void schedule(List<DeliveryPackage> packages, List<Vehicle> vehicles) {

        List<DeliveryPackage> remaining = new ArrayList<>(packages);

        // Sort all packages by heaviest first, if same weight lowest delivery time (distance/maxSpeed).
        remaining.sort(Comparator.comparingInt(DeliveryPackage::getWeight).reversed()
                .thenComparingInt(DeliveryPackage::getDistance));

        while (!remaining.isEmpty()) {
            Vehicle chosenVehicle = Collections.min(vehicles, Comparator.comparingDouble(Vehicle::getNextAvailable));
            // Create a shipment (group of pkgs for next trip)
            int shippedWeight = 0;
            List<DeliveryPackage> shipment = new ArrayList<>();
            Iterator<DeliveryPackage> iter = remaining.iterator();
            while (iter.hasNext()) {
                DeliveryPackage p = iter.next();
                if (p.getWeight() > chosenVehicle.getMaxWeight()) {
                    // Package cannot be shipped by any vehicle, skip it.
                    System.out.println("Package " + p.getId() + " cannot be shipped by any vehicle due to weight limit.");
                    iter.remove();
                    continue;
                }
                if (shippedWeight + p.getWeight() <= chosenVehicle.getMaxWeight()) {
                    shipment.add(p);
                    shippedWeight += p.getWeight();
                    iter.remove();
                }
            }

            // Delivery time is max(distance/speed) among shipment (all dropped off in one trip)
            double maxTime = shipment.stream()
                    .mapToDouble(pkg -> 1.0 * pkg.getDistance() / chosenVehicle.getMaxSpeed())
                    .max().orElse(0);
            for (DeliveryPackage pkg : shipment) {
                pkg.setDeliveryTime(Math.round((chosenVehicle.getNextAvailable() + 1.0 * pkg.getDistance() / chosenVehicle.getMaxSpeed()) * 100.0) / 100.0);
            }
            chosenVehicle.setNextAvailable(chosenVehicle.getNextAvailable() + 2 * maxTime);
        }
    }

}

package engineering.everest.courier.app.service.impl;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.entity.Vehicle;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Handles scheduling of package deliveries across available vehicles for a courier service.
 * <p>
 * Key responsibilities:
 * - Sort packages by priority (weight descending, then distance ascending)
 * - Assign packages to vehicles maximizing load without exceeding capacity
 * - Calculate and assign estimated delivery times
 * - Track vehicle availability time for subsequent shipments
 * <p>
 */
@Slf4j
public class DeliveryScheduler {

    /**
     * Schedules the given list of packages for delivery using the provided vehicles.
     * Mutates packages with delivery times, and vehicles with updated availability.
     *
     * @param packages list of packages to be delivered, assumed non-null
     * @param vehicles list of vehicles available, assumed non-null and contain at least one vehicle
     */
    public void schedule(List<DeliveryPackage> packages, List<Vehicle> vehicles) {

        log.info("Starting delivery scheduling for {} packages using {} vehicles", packages.size(), vehicles.size());
        // Defensive copy to avoid modifying input list reference
        List<DeliveryPackage> remaining = new ArrayList<>(packages);

        // Sort all packages by heaviest first, if same weight lowest delivery time (distance/maxSpeed).
        sortPackages(remaining);

        // Process shipments until no packages remain
        while (!remaining.isEmpty()) {
            Vehicle chosenVehicle = selectNextAvailableVehicle(vehicles);
            log.debug("Selected next available vehicle with maxSpeed: {}, maxWeight: {}, nextAvailable: {}",
                    chosenVehicle.getMaxSpeed(), chosenVehicle.getMaxWeight(), chosenVehicle.getNextAvailable());
            List<DeliveryPackage> shipment = pickShipment(remaining, chosenVehicle);
            if (shipment.isEmpty()) {
                // Safety check to prevent infinite loops if no packages fit vehicle
                log.warn("No shippable packages fit in vehicle with max weight {}, ending scheduling to prevent deadlock", chosenVehicle.getMaxWeight());
                break;
            }
            double tripTime = calculateTripTime(shipment, chosenVehicle);
            log.debug("Calculated trip time for shipment: {}", tripTime);
            assignDeliveryTimes(shipment, chosenVehicle);
            log.debug("Assigned delivery times for shipment size: {}", shipment.size());
            updateVehicleAvailability(chosenVehicle, tripTime);
            log.debug("Updated vehicle availability to {}", chosenVehicle.getNextAvailable());
        }
        log.info("Delivery scheduling completed");
    }

    /**
     * Sorts packages in-place by weight descending, then distance ascending.
     *
     * @param packages list of packages to sort
     */
    private void sortPackages(List<DeliveryPackage> packages) {
        packages.sort(
                Comparator.comparingInt(DeliveryPackage::getWeight).reversed()
                        .thenComparingInt(DeliveryPackage::getDistance)
        );
        log.debug("Sorted packages list with size: {}", packages.size());
    }

    /**
     * Selects the vehicle that becomes available the earliest.
     *
     * @param vehicles list of available vehicles
     * @return vehicle with minimum nextAvailable time
     */
    private Vehicle selectNextAvailableVehicle(List<Vehicle> vehicles) {
        Vehicle selected = Collections.min(vehicles, Comparator.comparingDouble(Vehicle::getNextAvailable));
        log.debug("Vehicle selected with nextAvailable time {}", selected.getNextAvailable());
        return selected;
    }


     /**
     * Selects packages to fill a shipment for the vehicle without exceeding max weight.
     * Removes packages that cannot be shipped by the vehicle due to weight.
     *
     * @param remaining packages to assign
     * @param vehicle   vehicle to load
     * @return list of packages assigned to this shipment
     */
    private List<DeliveryPackage> pickShipment(List<DeliveryPackage> remaining, Vehicle vehicle) {

        // Create a shipment (group of pkgs for next trip)
        List<DeliveryPackage> shipment = new ArrayList<>();
        int currentWeight = 0;

        Iterator<DeliveryPackage> iterator = remaining.iterator();
        while (iterator.hasNext()) {
            DeliveryPackage pkg = iterator.next();

            if (pkg.getWeight() > vehicle.getMaxWeight()) {
                // Skip packages too heavy for any vehicle
                log.warn("Package {} (weight {}) cannot be shipped by vehicle maxWeight {}", pkg.getId(), pkg.getWeight(), vehicle.getMaxWeight());
                iterator.remove();
                continue;
            }

            if (currentWeight + pkg.getWeight() <= vehicle.getMaxWeight()) {
                shipment.add(pkg);
                currentWeight += pkg.getWeight();
                log.debug("Added package {} to shipment, cumulative weight: {}", pkg.getId(), currentWeight);
                iterator.remove();
            }
        }
        log.debug("Shipment formed with {} packages, total weight {}", shipment.size(), currentWeight);
        return shipment;
    }

    /**
     * Calculates the trip time for the shipment as the max time taken by any package,
     * based on the package distance and vehicle speed.
     *
     * @param shipment list of packages in the shipment
     * @param vehicle  vehicle delivering the shipment
     * @return trip time in hours
     */
    private double calculateTripTime(List<DeliveryPackage> shipment, Vehicle vehicle) {
        // Shipment trip time is determined by the longest delivery time among its packages considering all the packages are on same route.
        return shipment.stream()
                .mapToDouble(pkg -> 1.0 * pkg.getDistance() / vehicle.getMaxSpeed())
                .max()
                .orElse(0);
    }

    /**
     * Assigns estimated delivery time to each package in the shipment based on vehicle availability and trip times.
     *
     * @param shipment shipment of packages
     * @param vehicle  vehicle used for shipment
     */
    private void assignDeliveryTimes(List<DeliveryPackage> shipment, Vehicle vehicle) {
        double vehicleAvailable = vehicle.getNextAvailable();
        for (DeliveryPackage pkg : shipment) {
            double deliveryTime = vehicleAvailable + 1.0 * pkg.getDistance() / vehicle.getMaxSpeed();
            pkg.setDeliveryTime(Math.round(deliveryTime * 100.0) / 100.0);
            log.debug("Assigned delivery time {} to package {}", pkg.getDeliveryTime(), pkg.getId());
        }
    }

    /**
     * Updates the vehicle availability time by adding round trip time (2 * tripTime).
     *
     * @param vehicle  vehicle to update
     * @param tripTime trip time for current shipment
     */
    private void updateVehicleAvailability(Vehicle vehicle, double tripTime) {
        double newAvailability = vehicle.getNextAvailable() + 2 * tripTime;
        vehicle.setNextAvailable(newAvailability);
        log.debug("Vehicle availability updated to {}", newAvailability);
    }

}

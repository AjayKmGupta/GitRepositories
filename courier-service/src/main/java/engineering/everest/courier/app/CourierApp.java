package engineering.everest.courier.app;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.entity.Offer;
import engineering.everest.courier.app.entity.Vehicle;
import engineering.everest.courier.app.service.CostCalculator;
import engineering.everest.courier.app.service.DeliveryScheduler;

import java.util.*;

public class CourierApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read base delivery cost and number of packages
        System.out.print("Enter base delivery cost: ");
        int baseDeliveryCost = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter number of packages: ");
        int numberOfPackages = scanner.nextInt();
        scanner.nextLine();

        // Read packages
        List<DeliveryPackage> packages = new ArrayList<>();
        for (int i = 0; i < numberOfPackages; i++) {
            System.out.print("Enter Package ID: ");
            String id = scanner.next();
            System.out.print("Enter Package Weight (kg): ");
            int weight = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Package Distance (km): ");
            int distance = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Offer Code (or 'NONE' if not applicable): ");
            String offerCode = scanner.next();
            packages.add(new DeliveryPackage(id, weight, distance, offerCode));
        }

        // Offers setup
        Map<String, Offer> offerMap = new HashMap<>();
        offerMap.put("OFR001", Offer.builder().code("OFR001").discountRate(0.10).minWeight(70).maxWeight(200).minDistance(0).maxDistance(199).build());
        offerMap.put("OFR002", Offer.builder().code("OFR002").discountRate(0.07).minWeight(100).maxWeight(250).minDistance(50).maxDistance(150).build());
        offerMap.put("OFR003", Offer.builder().code("OFR003").discountRate(0.05).minWeight(10).maxWeight(150).minDistance(50).maxDistance(250).build());

        // Cost Calculation
        CostCalculator calculator = new CostCalculator(baseDeliveryCost, offerMap);
        for (DeliveryPackage pkg : packages) {
            calculator.calculate(pkg);
        }

        // Output of the problem 1
        for (DeliveryPackage pkg : packages) {
            System.out.printf("%s %d %d %n", pkg.getId(), (int) pkg.getDiscount(), (int) pkg.getTotalCost());
        }

        // Read vehicle info
        System.out.print("Enter total number of vehicles: ");
        int totalVehicle = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter maximum speed of vehicle (km/h): ");
        int maxSpeed = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter maximum carriable weight of vehicle (kg): ");
        int maxWeight = scanner.nextInt();

        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < totalVehicle; i++) {
            Vehicle vehicle = new Vehicle(maxSpeed, maxWeight);
            vehicles.add(vehicle);
        }

        // Delivery scheduling
        DeliveryScheduler scheduler = new DeliveryScheduler();
        scheduler.schedule(packages, vehicles);

        // Output of the problem 2
        for (DeliveryPackage pkg : packages) {
            System.out.printf("%s %d %d %.2f%n", pkg.getId(), (int) pkg.getDiscount(), (int) pkg.getTotalCost(), pkg.getDeliveryTime());
        }
    }
}

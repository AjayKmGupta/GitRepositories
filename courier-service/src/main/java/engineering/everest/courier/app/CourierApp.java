package engineering.everest.courier.app;

import engineering.everest.courier.app.constants.CourierAppConstants;
import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.entity.Vehicle;
import engineering.everest.courier.app.service.impl.CostCalculator;
import engineering.everest.courier.app.service.impl.DeliveryScheduler;
import engineering.everest.courier.app.service.impl.OfferEvaluator;
import engineering.everest.courier.app.util.InputReader;

import java.util.*;

/**
 * Main class to run the Courier Application.
 * <p>
 * Orchestrates reading input data, calculating delivery costs, and scheduling deliveries.
 * Outputs results for delivery cost estimation and estimated delivery times.
 */
public class CourierApp {

    /**
     * Main entry point.
     * Reads application inputs, invokes business logic, and prints results to console.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Read base delivery cost and number of packages
        int baseDeliveryCost = InputReader.readIntCliInput(scanner, "Enter base delivery cost: ");
        int numberOfPackages = InputReader.readIntCliInput(scanner, "Enter number of packages: ");
        // Read package details
        List<DeliveryPackage> packages = InputReader.readPackages(scanner, numberOfPackages,
                baseDeliveryCost);
        // Set up offer evaluator with constant offer definitions
        OfferEvaluator offerEvaluator = new OfferEvaluator(CourierAppConstants.OFFER_MAP);
        // Calculate delivery costs and discounts for each package
        calculateTheDeliveryCost(baseDeliveryCost, packages, offerEvaluator);
        // Output delivery cost and discount results (Problem 1)
        // Display delivery cost and discount for each package (Problem 1)
        displayDeliveryCostAndDiscount(packages);

        // Read vehicle information
        List<Vehicle> vehicles = InputReader.readVehicles(scanner);
        // Schedule package deliveries using available vehicles (Problem 2)
        scheduleDeliveries(packages, vehicles);
        // Output package delivery times alongside costs (Problem 2)
        displayDeliveryCostAndDiscountAndDeliveryTime(packages);
        // Close scanner resource
        scanner.close();
    }

    /**
     * Schedules deliveries for the given list of packages using the provided vehicles.
     * Delegates scheduling logic to the DeliveryScheduler service.
     *
     * @param packages List of packages to be delivered
     * @param vehicles List of available vehicles for delivery
     */
    private static void scheduleDeliveries(List<DeliveryPackage> packages, List<Vehicle> vehicles) {
        // Create a DeliveryScheduler instance and schedule the packages
        DeliveryScheduler scheduler = new DeliveryScheduler();
        scheduler.schedule(packages, vehicles);
    }

    /**
     * Displays the delivery cost, discount, and estimated delivery time for each package.
     *
     * @param packages List of packages whose details are to be displayed
     */
    private static void displayDeliveryCostAndDiscountAndDeliveryTime(List<DeliveryPackage> packages) {
        for (DeliveryPackage pkg : packages) {
            // Print package ID, discount, total cost, and delivery time
            System.out.printf("%s %d %d %.2f%n",
                    pkg.getId(),
                    (int) pkg.getDiscount(),
                    (int) pkg.getTotalCost(),
                    pkg.getDeliveryTime());
        }
    }

    /**
     * Displays the delivery cost and discount for each package.
     *
     * @param packages List of packages whose details are to be displayed
     */
    private static void displayDeliveryCostAndDiscount(List<DeliveryPackage> packages) {
        for (DeliveryPackage pkg : packages) {
            // Print package ID, discount, and total cost
            System.out.printf("%s %d %d %n", pkg.getId(),
                    (int) pkg.getDiscount(),
                    (int) pkg.getTotalCost());
        }
    }

    /**
     * Calculates the delivery cost and discount for each package using the provided offer evaluator.
     *
     * @param baseDeliveryCost The base cost for delivery
     * @param packages List of packages to calculate costs for
     * @param offerEvaluator OfferEvaluator to determine applicable discounts
     */
    private static void calculateTheDeliveryCost(int baseDeliveryCost, List<DeliveryPackage> packages, OfferEvaluator offerEvaluator) {
        // Calculate delivery costs and discounts for each package
        CostCalculator calculator = new CostCalculator(baseDeliveryCost, offerEvaluator);
        for (DeliveryPackage pkg : packages) {
            calculator.calculate(pkg);
        }
    }
}

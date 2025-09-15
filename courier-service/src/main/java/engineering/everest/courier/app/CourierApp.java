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
        System.out.print("Enter base delivery cost: ");
        int baseDeliveryCost = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter number of packages: ");
        int numberOfPackages = scanner.nextInt();
        scanner.nextLine();

        // Read package details interactively
        List<DeliveryPackage> packages = InputReader.readPackages(scanner, numberOfPackages,
                baseDeliveryCost);

        // Set up offer evaluator with constant offer definitions
        OfferEvaluator offerEvaluator = new OfferEvaluator(CourierAppConstants.OFFER_MAP);

        // Calculate delivery costs and discounts for each package
        CostCalculator calculator = new CostCalculator(baseDeliveryCost, offerEvaluator);
        for (DeliveryPackage pkg : packages) {
            calculator.calculate(pkg);
        }

        // Output delivery cost and discount results (Problem 1)
        for (DeliveryPackage pkg : packages) {
            System.out.printf("%s %d %d %n", pkg.getId(),
                    (int) pkg.getDiscount(),
                    (int) pkg.getTotalCost());
        }

        // Read vehicle information
        List<Vehicle> vehicles = InputReader.readVehicles(scanner);

        // Schedule package deliveries using available vehicles (Problem 2)
        DeliveryScheduler scheduler = new DeliveryScheduler();
        scheduler.schedule(packages, vehicles);

        // Output package delivery times alongside costs (Problem 2)
        for (DeliveryPackage pkg : packages) {
            System.out.printf("%s %d %d %.2f%n",
                    pkg.getId(),
                    (int) pkg.getDiscount(),
                    (int) pkg.getTotalCost(),
                    pkg.getDeliveryTime());
        }
        // Close scanner resource
        scanner.close();
    }
}

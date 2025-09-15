package engineering.everest.courier.app.util;

import engineering.everest.courier.app.entity.DeliveryPackage;
import engineering.everest.courier.app.entity.Vehicle;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for reading delivery packages and vehicle information from input.
 * <p>
 * Contains static methods to interactively read package and vehicle data via a {@link Scanner}.
 * Designed as a non-instantiable utility class.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InputReader {

    /**
     * Reads details of delivery packages from the input.
     * Prompts user for package ID, weight, distance, and offer code for each package.
     * The number of packages and max vehicle weight are parameters but maxWeight is currently unused
     * (can be used to validate packages in future extensions).
     *
     * @param scanner          the {@link Scanner} object used for reading input
     * @param numberOfPackages the total number of packages to read
     * @param maxWeight        the maximum carriable weight of vehicles (not used in this method currently)
     * @return a list of {@link DeliveryPackage} objects reading from input
     */
    public static List<DeliveryPackage> readPackages(Scanner scanner, int numberOfPackages, int maxWeight) {

        List<DeliveryPackage> packages = new ArrayList<>();

        // Read packages
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
            DeliveryPackage pkg = DeliveryPackage.builder()
                    .id(id).weight(weight).distance(distance).offerCode(offerCode).build();
            packages.add(pkg);
        }
        return packages;
    }

    /**
     * Reads information about vehicles from input.
     * Prompts user for number of vehicles, max speed, and max carriable weight.
     * Creates the specified number of {@link Vehicle} objects with given specs.
     *
     * @param scanner the {@link Scanner} object used for reading input
     * @return a list of {@link Vehicle} objects initialized as per user input
     */
    public static List<Vehicle> readVehicles(Scanner scanner) {
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
            Vehicle vehicle = Vehicle.builder().maxSpeed(maxSpeed).maxWeight(maxWeight).build();
            vehicles.add(vehicle);
        }
        return vehicles;
    }
}

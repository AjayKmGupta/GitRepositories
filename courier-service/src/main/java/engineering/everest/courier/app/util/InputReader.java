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
            String id = readStringCliInput(scanner, "Enter Package ID: ");
            int weight = readIntCliInput(scanner, "Enter Package Weight (kg): ");
            int distance = readIntCliInput(scanner, "Enter Package Distance (km): ");
            String offerCode = readStringCliInput(scanner, "Enter Offer Code (or 'NONE' if not applicable): ");
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
        int totalVehicle = readIntCliInput(scanner, "Enter total number of vehicles: ");
        int maxSpeed = readIntCliInput(scanner, "Enter maximum speed of vehicle (km/h): ");
        int maxWeight = readIntCliInput(scanner, "Enter maximum carriable weight of vehicle (kg): ");
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < totalVehicle; i++) {
            Vehicle vehicle = Vehicle.builder().maxSpeed(maxSpeed).maxWeight(maxWeight).build();
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    public static String readStringCliInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int readIntCliInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return value;
    }
}

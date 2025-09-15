# Courier Service Application

## Overview

This application calculates the optimal delivery schedule and costs for a courier service. It takes package details (weight, distance, offer code) as input, evaluates applicable offers, and determines the delivery time for each package based on available vehicles and their maximum carrying capacity.

## Functionality

-   Calculates the cost of delivery for each package based on weight and distance.
-   Applies available offers to reduce the delivery cost.
-   Schedules package deliveries using available vehicles, optimizing delivery time.
-   Provides the delivery schedule, including estimated delivery time and discounted cost for each package.

## Dependencies

-   Java 17 or higher
-   Maven

## Setup

1.  Install Java 17 or higher.
2.  Install Maven.
3.  Clone the repository:

    ```sh
    git clone <repository_url>
    ```
4.  Navigate to the project directory:

    ```sh
    cd courier-service
    ```
5.  Build the project using Maven:

    ```sh
    mvn clean install
    ```

## Usage

To run the application, execute the following command:

```sh
mvn exec:java -Dexec.mainClass="enginee100ring.everest.courier.app.CourierApp"
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner sc = new Scanner(System.in);

        // Create an Inventory object to manage vehicles
        Inventory inventory = new Inventory();

        // Main program loop
        while (true) {
            // Display menu options
            System.out.println("\n--- Vehicle Inventory Management ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Update Vehicle Availability");
            System.out.println("4. Book Vehicle");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            // Read the user's menu choice
            int choice = sc.nextInt();
            sc.nextLine(); // Clear the newline left by nextInt()

            // Option 1: View all vehicles in the inventory
            if (choice == 1) {
                inventory.viewInventory();

            // Option 2: Add a new vehicle to the inventory
            } else if (choice == 2) {
                System.out.print("Enter Vehicle ID: ");
                String id = sc.nextLine();

                System.out.print("Enter Brand & Model: ");
                String brand = sc.nextLine();

                System.out.print("Enter Mileage (km): ");
                int mileage = sc.nextInt();

                System.out.print("Enter Daily Rental Price: ");
                double price = sc.nextDouble();

                System.out.print("Enter Maintenance Cost per Km: ");
                double maintenanceCost = sc.nextDouble();
                sc.nextLine(); // Clear newline

                System.out.print("Is Available? (true/false): ");
                boolean available = sc.nextBoolean();
                sc.nextLine(); // Clear newline

                // Create a new Vehicle object and add it to the inventory
                Vehicle newVehicle = new Vehicle(id, brand, mileage, price, maintenanceCost, available);
                inventory.addVehicle(newVehicle);

            // Option 3: Update availability of an existing vehicle
            } else if (choice == 3) {
                System.out.print("Enter Vehicle ID to update availability: ");
                String id = sc.nextLine();

                System.out.print("Enter new availability (true/false): ");
                boolean availability = sc.nextBoolean();
                sc.nextLine(); // Clear newline

                inventory.updateAvailability(id, availability);

            // Option 4: Book a vehicle
            } else if (choice == 4) {
                inventory.bookVehicle(sc);

            // Option 5: Exit the program
            } else if (choice == 5) {
                System.out.println("Exiting program. Bye!");
                break;

            // Invalid menu choice
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        // Close the Scanner to free resources
        sc.close();
    }
}

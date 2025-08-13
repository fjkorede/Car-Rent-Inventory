import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner to read user input
        Inventory inventory = new Inventory(); // Load saved inventory or sample vehicles

        while (true) {
            // Display the menu options
            System.out.println("\n--- Vehicle Inventory Management ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Update Vehicle Availability");
            System.out.println("4. Book Vehicle");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String input = sc.nextLine(); // Read input as String for safety
            int choice;

            // Try converting input to an integer
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option"); // Input is not a number
                continue; // Go back to the menu
            }

            // Process menu choices
            if (choice == 1) {
                // View the current inventory
                inventory.viewInventory();

            } else if (choice == 2) {
                // Add a new vehicle
                try {
                    System.out.print("Enter Vehicle ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Brand & Model: ");
                    String brand = sc.nextLine();

                    System.out.print("Enter Mileage (km): ");
                    int mileage = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Daily Rental Price: ");
                    double price = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter Maintenance Cost per Km: ");
                    double maintenanceCost = Double.parseDouble(sc.nextLine());

                    System.out.print("Is Available? (true/false): ");
                    boolean available = Boolean.parseBoolean(sc.nextLine());

                    Vehicle newVehicle = new Vehicle(id, brand, mileage, price, maintenanceCost, available);
                    inventory.addVehicle(newVehicle);

                } catch (Exception e) {
                    // Any input parsing error will trigger this
                    System.out.println("Invalid option");
                }

            } else if (choice == 3) {
                // Update availability of a vehicle
                try {
                    System.out.print("Enter Vehicle ID to update availability: ");
                    String id = sc.nextLine();

                    System.out.print("Enter new availability (true/false): ");
                    boolean availability = Boolean.parseBoolean(sc.nextLine());

                    inventory.updateAvailability(id, availability);
                } catch (Exception e) {
                    System.out.println("Invalid option"); // Wrong input format
                }

            } else if (choice == 4) {
                // Book a vehicle
                try {
                    inventory.bookVehicle(sc);
                } catch (Exception e) {
                    System.out.println("Invalid option"); // Wrong input for booking
                    sc.nextLine(); // Clear leftover scanner buffer
                }

            } else if (choice == 5) {
                // Exit the program
                System.out.println("Exiting program. Bye!");
                break;

            } else {
                // Any number not 1-5
                System.out.println("Invalid option");
            }
        }

        sc.close(); // Close scanner before exiting
    }
}

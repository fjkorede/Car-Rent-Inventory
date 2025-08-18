import java.util.Scanner;

// Main class (entry point of the program)
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // For user input
        Inventory inventory = new Inventory(); // Create an inventory with 10 sample vehicles

        while (true) {
            // Show menu options
            System.out.println("\n--- Vehicle Inventory Management ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Update Vehicle Availability");
            System.out.println("4. Book Vehicle");
            System.out.println("5. Return Vehicle");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine()); // Convert input to number
            } catch (Exception e) {
                System.out.println("Invalid option! Please enter a number.");
                continue; // back to menu
            }

            // Handle choices
            if (choice == 1) {
                inventory.viewInventory();

            } else if (choice == 2) {
                // Add new vehicle
                System.out.print("Enter Vehicle ID: ");
                String id = sc.nextLine();

                System.out.print("Enter Brand & Model: ");
                String model = sc.nextLine();

                System.out.print("Enter Mileage (km): ");
                int mileage = Integer.parseInt(sc.nextLine());

                System.out.print("Enter Daily Rental Price: ");
                double price = Double.parseDouble(sc.nextLine());

                // By default, new vehicle is available
                Vehicle newVehicle = new Vehicle(id, model, mileage, price, true);
                inventory.addVehicle(newVehicle);

            } else if (choice == 3) {
                // Update availability
                System.out.print("Enter Vehicle ID: ");
                String id = sc.nextLine();

                System.out.print("Enter new availability (true/false): ");
                boolean available = Boolean.parseBoolean(sc.nextLine());

                inventory.updateAvailability(id, available);

            } else if (choice == 4) {
                // Book vehicle
                inventory.bookVehicle(sc);

            } else if (choice == 5) {
                // Return vehicle
                inventory.returnVehicle(sc);

            } else if (choice == 6) {
                // Exit program
                System.out.println("Exiting program. Goodbye!");
                break;

            } else {
                System.out.println("Invalid option. Please choose 1-6.");
            }
        }

        sc.close(); // Close scanner
    }
}

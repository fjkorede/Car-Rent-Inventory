import java.util.Scanner;

// Main class 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);   // For user input
        Inventory inventory = new Inventory(); // Inventory manager

        while (true) {
            // Show menu options
            System.out.println("\n--- Vehicle Inventory Management ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Update Vehicle Availability");
            System.out.println("4. Book Vehicle");
            System.out.println("5. Return Vehicle");
            System.out.println("6. View Transactions"); // 🔹 NEW
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine()); // Convert input to number
            } catch (Exception e) {
                System.out.println("Invalid option! Please enter a number.");
                continue; // back to menu
            }

            // Handle choices
            switch (choice) {
                case 1:
                    inventory.displayVehicles();
                    break;

                case 2:
                    // Add new vehicle
                    System.out.print("Enter Vehicle ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Brand & Model: ");
                    String model = sc.nextLine();

                    System.out.print("Enter Mileage (km): ");
                    int mileage = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Daily Rental Price: ");
                    double price = Double.parseDouble(sc.nextLine());

                    Vehicle newVehicle = new Vehicle(id, model, mileage, price, true);
                    inventory.addVehicle(newVehicle);
                    System.out.println("Vehicle added successfully!");
                    break;

                case 3:
                    // Update availability
                    System.out.print("Enter Vehicle ID: ");
                    String updateId = sc.nextLine();

                    System.out.print("Enter new availability (true/false): ");
                    boolean available = Boolean.parseBoolean(sc.nextLine());

                    inventory.updateAvailability(updateId, available);
                    break;

                case 4:
                    inventory.bookVehicle(sc);
                    break;

                case 5:
                    inventory.returnVehicle(sc);
                    break;

                case 6:
                    inventory.displayTransactions(); // 🔹 show transaction log
                    break;

                case 7:
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose 1-7.");
            }
        }
    }
}

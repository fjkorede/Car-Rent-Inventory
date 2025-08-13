import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\n--- Vehicle Inventory Management ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Update Vehicle Availability");
            System.out.println("4. Book Vehicle");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Clear newline

            if (choice == 1) {
                inventory.viewInventory();

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

                Vehicle newVehicle = new Vehicle(id, brand, mileage, price, maintenanceCost, available);
                inventory.addVehicle(newVehicle);

            } else if (choice == 3) {
                System.out.print("Enter Vehicle ID to update availability: ");
                String id = sc.nextLine();

                System.out.print("Enter new availability (true/false): ");
                boolean availability = sc.nextBoolean();
                sc.nextLine(); // Clear newline

                inventory.updateAvailability(id, availability);

            } else if (choice == 4) {
                inventory.bookVehicle(sc);

            } else if (choice == 5) {
                System.out.println("Exiting program. Bye!");
                break;

            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }
}

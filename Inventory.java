import java.util.ArrayList;
import java.util.Scanner;

// The Inventory class manages all the vehicles (like a garage list)
public class Inventory {
    // This list will hold all our vehicles
    private ArrayList<Vehicle> vehicles;

    // Constructor: creates the list and adds some sample vehicles
    public Inventory() {
        vehicles = new ArrayList<>();

        // Add 10 sample vehicles (preloaded so we always see something)
        vehicles.add(new Vehicle("V1", "Toyota Corolla", 50000, 50.0, true));
        vehicles.add(new Vehicle("V2", "Honda Civic", 60000, 55.0, true));
        vehicles.add(new Vehicle("V3", "Ford Focus", 45000, 45.0, true));
        vehicles.add(new Vehicle("V4", "Nissan Altima", 70000, 52.0, true));
        vehicles.add(new Vehicle("V5", "Hyundai Elantra", 40000, 48.0, true));
        vehicles.add(new Vehicle("V6", "Kia Rio", 35000, 40.0, true));
        vehicles.add(new Vehicle("V7", "Chevrolet Malibu", 65000, 60.0, true));
        vehicles.add(new Vehicle("V8", "BMW 3 Series", 30000, 120.0, true));
        vehicles.add(new Vehicle("V9", "Mercedes C-Class", 28000, 130.0, true));
        vehicles.add(new Vehicle("V10", "Audi A4", 32000, 125.0, true));
    }

    // Show all vehicles in a table format
    public void viewInventory() {
        System.out.println("\n--- Vehicle Inventory ---");

        // Print table headers
        System.out.printf("%-6s %-18s %-11s %-12s %-10s%n",
            "ID", "Brand & Model", "Mileage", "Daily Price", "Status");
        System.out.println("--------------------------------------------------------------");

        // Loop through the list and print each vehicle
        for (Vehicle v : vehicles) {
            String status = v.isAvailable() ? "Available" : "Booked";
            System.out.printf("%-6s %-18s %-11s $%-11.2f %-10s%n",
                v.getVehicleId(),
                v.getBrandModel(),
                v.getMileage() + "km",
                v.getDailyRentalPrice(),
                status);
        }
    }

    // Add a new vehicle (entered by the user)
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle); // Add to the list
        System.out.println("Vehicle added successfully!");
    }

    // Change availability (true = available, false = booked)
    public void updateAvailability(String id, boolean available) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                v.setAvailable(available);
                System.out.println("Availability updated for vehicle " + id);
                return; // Stop after updating
            }
        }
        System.out.println("Vehicle ID not found.");
    }

    // Book a vehicle
    public void bookVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to book: ");
        String id = sc.nextLine();

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                if (!v.isAvailable()) {
                    System.out.println("Sorry, this vehicle is already booked.");
                    return;
                }

                // Ask how many days
                System.out.print("Enter rental duration (days): ");
                int days = sc.nextInt();
                sc.nextLine(); // Clear input buffer

                // Calculate total price
                double cost = v.getDailyRentalPrice() * days;

                // Mark as booked
                v.setAvailable(false);

                System.out.println("Booking successful! Total cost: $" + cost);
                return;
            }
        }
        System.out.println("Vehicle ID not found.");
    }

    // Return a vehicle
   // Return vehicle with fees
public void returnVehicle(Scanner sc) {
    System.out.print("Enter Vehicle ID to return: ");
    String id = sc.nextLine();

    // Loop through vehicles to find the one with this ID
    for (Vehicle v : vehicles) {
        if (v.getVehicleId().equalsIgnoreCase(id)) {
            
            // If the vehicle was never booked
            if (v.isAvailable()) {
                System.out.println("This vehicle was not booked.");
                return;
            }

            // Ask for kilometers driven
            System.out.print("Enter kilometers driven: ");
            int km = sc.nextInt();

            // Ask for how many days late
            System.out.print("Enter days late (0 if none): ");
            int lateDays = sc.nextInt();
            sc.nextLine(); // clear buffer

            // Fixed fees
            int cleaningFee = 20;              // always 20
            int maintenance = km * 1;          // €1 per km
            int lateFee = lateDays * 10;       // €10 per late day

            // Calculate total charges
            int total = cleaningFee + maintenance + lateFee;

            // Mark vehicle available again
            v.setAvailable(true);

            // Show a breakdown of charges
            System.out.println("\n--- Return Summary ---");
            System.out.println("Cleaning Fee:   €" + cleaningFee);
            System.out.println("Maintenance:    €" + maintenance);
            System.out.println("Late Fee:       €" + lateFee);
            System.out.println("TOTAL Charges:  €" + total);

            System.out.println("Vehicle returned successfully!");
            return;
        }
    }

    // If ID is not found at all
    System.out.println("Vehicle ID not found.");
}

}

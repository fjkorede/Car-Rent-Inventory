import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    // List to store all Vehicle objects
    private ArrayList<Vehicle> vehicles;

    // Constructor: creates the ArrayList and seeds it with initial data
    public Inventory() {
        vehicles = new ArrayList<Vehicle>();
        seedData(); // add some sample vehicles
    }

    // Adds sample vehicles to the inventory when the program starts
    private void seedData() {
        vehicles.add(new Vehicle("V01", "Toyota Corolla", 45000, 50.0, 0.20, true));
        vehicles.add(new Vehicle("V02", "Honda Civic", 30000, 55.0, 0.25, true));
        vehicles.add(new Vehicle("V03", "Ford Focus", 60000, 45.0, 0.18, false));
        vehicles.add(new Vehicle("V04", "Hyundai Elantra", 25000, 48.0, 0.22, true));
        vehicles.add(new Vehicle("V05", "Nissan Altima", 35000, 52.0, 0.24, true));
        vehicles.add(new Vehicle("V06", "Kia Optima", 50000, 49.0, 0.21, false));
        vehicles.add(new Vehicle("V07", "Chevrolet Malibu", 40000, 50.0, 0.20, true));
        vehicles.add(new Vehicle("V08", "Volkswagen Passat", 28000, 53.0, 0.23, true));
        vehicles.add(new Vehicle("V09", "Mazda 6", 42000, 51.0, 0.19, true));
        vehicles.add(new Vehicle("V10", "BMW 3 Series", 15000, 80.0, 0.35, true));
    }

    // Displays the full inventory in a table format
    public void viewInventory() {
        System.out.printf("%-6s %-18s %-11s %-12s %-15s %-10s%n",
            "ID", "Brand & Model", "Mileage", "Daily Price", "Maint. Cost/km", "Status");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            String status = v.isAvailable() ? "Available" : "Unavailable"; // check availability
            String mileageWithUnit = v.getMileage() + "km";

            // Print vehicle details in formatted table
            System.out.printf("%-6s %-18s %-11s $%-11.2f $%-14.2f %-10s%n",
                v.getVehicleId(),
                v.getBrandModel(),
                mileageWithUnit,
                v.getDailyRentalPrice(),
                v.getMaintenanceCostPerKm(),
                status);
        }
    }

    // Adds a new vehicle to the inventory
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle added successfully!");
    }

    // Updates the availability status of a vehicle by its ID
    public void updateAvailability(String vehicleId, boolean availability) {
        boolean found = false;

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (v.getVehicleId().equalsIgnoreCase(vehicleId)) {
                v.setAvailable(availability); // update availability
                System.out.println("Availability updated for Vehicle ID: " + vehicleId);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Vehicle ID not found!"); // if no match found
        }
    }

    // Allows a user to book a vehicle
    public void bookVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to book: ");
        String id = sc.nextLine();

        Vehicle vehicleToBook = null;

        // Find the vehicle in the list
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleId().equalsIgnoreCase(id)) {
                vehicleToBook = vehicles.get(i);
                break;
            }
        }

        if (vehicleToBook == null) {
            System.out.println("Vehicle ID not found.");
            return;
        }

        if (!vehicleToBook.isAvailable()) {
            System.out.println("Sorry, this vehicle is currently unavailable.");
            return;
        }

        // Ask user for rental duration and estimated distance
        System.out.print("Enter rental duration in days: ");
        int rentalDays = sc.nextInt();

        System.out.print("Enter estimated kilometers to drive: ");
        int estimatedKm = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        // Calculate total rental cost
        double cost = (vehicleToBook.getDailyRentalPrice() * rentalDays)
                    + (vehicleToBook.getMaintenanceCostPerKm() * estimatedKm);

        // Mark the vehicle as no longer available
        vehicleToBook.setAvailable(false);

        System.out.println("Booking successful!");
        System.out.printf("Estimated total cost: $%.2f%n", cost);
    }
}

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Inventory {
    // This will hold all the vehicles in memory
    private ArrayList<Vehicle> vehicles;

    // File name to store vehicle data (will be created in your project folder)
    private final String fileName = "vehicles.txt";

    // Constructor: runs when we create an Inventory object
    public Inventory() {
        vehicles = new ArrayList<>();
        loadFromFile(); // Load saved vehicles when program starts
    }

    /**
     * Loads vehicle data from the file into the ArrayList.
     * If the file doesn't exist, we just start with an empty list.
     */
    public void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Read file line by line
            while ((line = reader.readLine()) != null) {
                // Each vehicle's info is separated by commas
                String[] parts = line.split(",");

                // Create a Vehicle object from the file data
                Vehicle v = new Vehicle(
                    parts[0], // Vehicle ID
                    parts[1], // Brand & Model
                    Integer.parseInt(parts[2]), // Mileage
                    Double.parseDouble(parts[3]), // Daily rental price
                    Double.parseDouble(parts[4]), // Maintenance cost per km
                    Boolean.parseBoolean(parts[5]) // Availability
                );

                // Add vehicle to the ArrayList
                vehicles.add(v);
            }

            reader.close(); // Close file after reading
        } 
        catch (FileNotFoundException e) {
            // If file doesn't exist, that's fine — we'll create it later
            System.out.println("No previous data found, starting fresh.");
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Saves all vehicle data from the ArrayList to the file.
     * This is called whenever a change is made.
     */
    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(fileName);

            // Loop through each vehicle and write its details
            for (Vehicle v : vehicles) {
                writer.println(v.getVehicleId() + "," +
                               v.getBrandModel() + "," +
                               v.getMileage() + "," +
                               v.getDailyRentalPrice() + "," +
                               v.getMaintenanceCostPerKm() + "," +
                               v.isAvailable());
            }

            writer.close(); // Close file after writing
        } 
        catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Displays all vehicles in a table format.
     */
    public void viewInventory() {
        System.out.printf("%-6s %-18s %-11s %-12s %-15s %-10s%n",
            "ID", "Brand & Model", "Mileage", "Daily Price", "Maint. Cost/km", "Status");
        System.out.println("----------------------------------------------------------------------");

        // Loop through vehicles and print details
        for (Vehicle v : vehicles) {
            String status = v.isAvailable() ? "Available" : "Unavailable";
            String mileageWithUnit = v.getMileage() + "km";

            System.out.printf("%-6s %-18s %-11s $%-11.2f $%-14.2f %-10s%n",
                v.getVehicleId(),
                v.getBrandModel(),
                mileageWithUnit,
                v.getDailyRentalPrice(),
                v.getMaintenanceCostPerKm(),
                status);
        }
    }

    /**
     * Adds a new vehicle to the inventory and saves to file.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle); // Add to list
        saveToFile(); // Save changes to file immediately
        System.out.println("Vehicle added successfully!");
    }

    /**
     * Updates availability of a specific vehicle and saves changes.
     */
    public void updateAvailability(String vehicleId, boolean availability) {
        boolean found = false;

        // Search for the vehicle by ID
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(vehicleId)) {
                v.setAvailable(availability); // Update status
                saveToFile(); // Save changes
                System.out.println("Availability updated for Vehicle ID: " + vehicleId);
                found = true;
                break; // Stop loop once found
            }
        }

        if (!found) {
            System.out.println("Vehicle ID not found!");
        }
    }

    /**
     * Books a vehicle if available, updates its status, and calculates cost.
     */
    public void bookVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to book: ");
        String id = sc.nextLine();

        Vehicle vehicleToBook = null;

        // Find the vehicle by ID
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                vehicleToBook = v;
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

        // Get rental details from user
        System.out.print("Enter rental duration in days: ");
        int rentalDays = sc.nextInt();

        System.out.print("Enter estimated kilometers to drive: ");
        int estimatedKm = sc.nextInt();
        sc.nextLine(); // Consume leftover newline

        // Calculate total cost
        double cost = (vehicleToBook.getDailyRentalPrice() * rentalDays)
                    + (vehicleToBook.getMaintenanceCostPerKm() * estimatedKm);

        // Mark vehicle as unavailable
        vehicleToBook.setAvailable(false);
        saveToFile(); // Save updated status

        System.out.println("Booking successful!");
        System.out.printf("Estimated total cost: $%.2f%n", cost);
    }
}

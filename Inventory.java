import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// The Inventory class manages all the vehicles (like a garage list)
public class Inventory {
    private ArrayList<Vehicle> vehicles;
    private final String FILE_NAME = "vehicles.txt"; // file to save/load vehicles

    // Constructor: load from file OR add 10 sample vehicles if file is empty
    public Inventory() {
        vehicles = new ArrayList<>();
        loadVehicles(); // try to load from file

        if (vehicles.isEmpty()) {
            // if no file yet, preload 10 vehicles
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
            saveVehicles(); // save them so they appear next time
        }
    }

    // Show all vehicles
    public void viewInventory() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the inventory.");
            return;
        }

        for (Vehicle v : vehicles) {
            String status;
            if (v.isNeedsMaintenance()) {
                status = "Maintenance";
            } else {
                status = v.isAvailable() ? "Available" : "Booked";
            }

            System.out.println(
                v.getVehicleId() + " | " + v.getBrandModel() + " | "
                + v.getMileage() + " km | " + status
            );
        }
    }

    // Add a new vehicle
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        saveVehicles(); // save to file
        checkMaintenance(vehicle);  // check mileage immediately
        System.out.println("Vehicle added successfully!");
    }

    // Update availability
    public void updateAvailability(String id, boolean available) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                v.setAvailable(available);
                saveVehicles(); // save changes
                System.out.println("Availability updated for vehicle " + id);
                return;
            }
        }
        System.out.println("Vehicle ID not found.");
    }

    // Book vehicle
    public void bookVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to book: ");
        String id = sc.nextLine();

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                if (!v.isAvailable()) {
                    System.out.println("Sorry, this vehicle is already booked.");
                    return;
                }

                System.out.print("Enter rental duration (days): ");
                int days = Integer.parseInt(sc.nextLine());
                double cost = v.getDailyRentalPrice() * days;

                v.setAvailable(false);
                saveVehicles(); // save changes
                System.out.println("Booking successful! Total cost: $" + cost);
                return;
            }
        }
        System.out.println("Vehicle ID not found.");
    }

    // Return vehicle
    public void returnVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID to return: ");
        String id = sc.nextLine();

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                if (v.isAvailable()) {
                    System.out.println("This vehicle was not booked.");
                    return;
                }

                System.out.print("Enter kilometers driven: ");
                int km = Integer.parseInt(sc.nextLine());

                System.out.print("Enter days late (0 if none): ");
                int lateDays = Integer.parseInt(sc.nextLine());

                int cleaningFee = 20;
                int maintenance = km * 1;
                int lateFee = lateDays * 10;
                int total = cleaningFee + maintenance + lateFee;

                // ✅ update mileage before checking maintenance
                v.setMileage(v.getMileage() + km);

                // ✅ check maintenance after mileage update
                checkMaintenance(v);

                v.setAvailable(true);
                saveVehicles(); // save changes

                System.out.println("\n--- Return Summary ---");
                System.out.println("Cleaning Fee:   €" + cleaningFee);
                System.out.println("Maintenance:    €" + maintenance);
                System.out.println("Late Fee:       €" + lateFee);
                System.out.println("TOTAL Charges:  €" + total);
                System.out.println("Vehicle returned successfully!");
                return;
            }
        }
        System.out.println("Vehicle ID not found.");
    }

    // Basic check for maintenance
    private void checkMaintenance(Vehicle v) {
        if (v.getMileage() > 10000) {
            v.setNeedsMaintenance(true);  // mark it
            double cost = v.getMileage() * 0.05; // simple cost formula
            v.setMaintenanceCost(cost);
            System.out.println(
                "Vehicle " + v.getVehicleId()
                + " needs maintenance! Cost: $" + cost
            );
        }
    }

    // ===== FILE SAVE/LOAD =====
    private void saveVehicles() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Vehicle v : vehicles) {
                pw.println(
                    v.getVehicleId() + "," + v.getBrandModel() + ","
                    + v.getMileage() + "," + v.getDailyRentalPrice() + ","
                    + v.isAvailable()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    private void loadVehicles() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String model = parts[1];
                    int mileage = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    boolean available = Boolean.parseBoolean(parts[4]);
                    vehicles.add(new Vehicle(id, model, mileage, price, available));
                }
            }
        } catch (IOException e) {
            // ignore if file doesn’t exist yet
        }
    }
}

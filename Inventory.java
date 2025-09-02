import java.io.*;
import java.util.*;

// Inventory class stores and manages the list of vehicles
public class Inventory {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    // Load vehicles from file or start empty
    public Inventory() {
        loadVehicles();
    }

    // Add a new vehicle
    public void addVehicle(Vehicle v) {
        vehicles.add(v);
        saveVehicles();
    }

    // Show all vehicles
    public void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in this inventory.");
            return;
        }

        System.out.println("\n--- Vehicle List ---");
        for (Vehicle v : vehicles) {
            System.out.println(
                "ID: " + v.getVehicleId() +
                " | Model: " + v.getBrandModel() +
                " | Mileage: " + v.getMileage() +
                " km | Price/day: €" + v.getDailyRentalPrice() +
                " | Available: " + v.isAvailable()
            );
        }
    }

    // Book vehicle
public void bookVehicle(Scanner sc) {
    System.out.print("Enter Vehicle ID to book: ");
    String id = sc.nextLine();

    for (Vehicle v : vehicles) {
        if (v.getVehicleId().equalsIgnoreCase(id) && v.isAvailable()) {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Enter number of days: ");
            int days = Integer.parseInt(sc.nextLine());

            double cost = days * v.getDailyRentalPrice();
            v.setAvailable(false);
            v.setBookedDays(days);
            saveVehicles();

            // 🔹 Save booking transaction (initial revenue, no extra costs yet)
            transactions.add(new Transaction(name, id, days, cost, 0));

            System.out.println("Vehicle booked! Total cost: €" + cost);
            return;
        }
    }
    System.out.println("Vehicle not found or not available.");
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
            int totalExtra = cleaningFee + maintenance + lateFee;

            v.setMileage(v.getMileage() + km);
            v.setAvailable(true);
            v.setBookedDays(0);

            checkMaintenance(v);
            saveVehicles();

            // 🔹 Log return transaction (no revenue, just extra costs)
            transactions.add(new Transaction("Unknown", id, 0, 0, totalExtra));

            System.out.println("\n--- Return Summary ---");
            System.out.println("Cleaning Fee:   €" + cleaningFee);
            System.out.println("Maintenance:    €" + maintenance);
            System.out.println("Late Fee:       €" + lateFee);
            System.out.println("TOTAL Charges:  €" + totalExtra);
            System.out.println("Vehicle returned successfully!");
            return;
        }
    }
    System.out.println("Vehicle ID not found.");
}


     // Show all transactions
    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.println("\n--- Transaction History ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    // Basic check for maintenance
    private void checkMaintenance(Vehicle v) {
        if (v.getMileage() > 10000) {
            v.setNeedsMaintenance(true);
            double cost = v.getMileage() * 0.05; // simple cost formula
            v.setMaintenanceCost(cost);

            System.out.println(
                "⚠ Vehicle " + v.getVehicleId() +
                " needs maintenance! Cost: €" + cost
            );
        }
    }

    // Save vehicles to file
    private void saveVehicles() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("vehicles.txt"))) {
            for (Vehicle v : vehicles) {
                pw.println(
                    v.getVehicleId() + "," +
                    v.getBrandModel() + "," +
                    v.getMileage() + "," +
                    v.getDailyRentalPrice() + "," +
                    v.isAvailable()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    // Load vehicles from file
    private void loadVehicles() {
        File file = new File("vehicles.txt");
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length == 5) {
                    Vehicle v = new Vehicle(
                        parts[0],                     // ID
                        parts[1],                     // BrandModel
                        Integer.parseInt(parts[2]),   // Mileage
                        Double.parseDouble(parts[3]), // Price/day
                        Boolean.parseBoolean(parts[4])// Available
                    );
                    vehicles.add(v);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading vehicles: " + e.getMessage());
        }
    }

    // Update availability of a vehicle by ID
    public void updateAvailability(String vehicleId, boolean available) {
    for (Vehicle v : vehicles) {
        if (v.getVehicleId().equalsIgnoreCase(vehicleId)) {
            v.setAvailable(available);
            System.out.println("Vehicle " + vehicleId + " availability updated to " + available);
            return;
        }
    }
    System.out.println("Vehicle with ID " + vehicleId + " not found.");
}

}

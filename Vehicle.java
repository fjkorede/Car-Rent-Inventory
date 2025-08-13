public class Vehicle {
    // Private fields to store vehicle information
    private String vehicleId;           // Unique ID for the vehicle
    private String brandModel;          // Brand and model name
    private int mileage;                // Vehicle mileage in km
    private double dailyRentalPrice;    // Price to rent per day
    private double maintenanceCostPerKm;// Maintenance cost per kilometer
    private boolean available;          // Whether the vehicle is available for rent

    // Constructor: used to create a new Vehicle object with all details
    public Vehicle(String vehicleId, String brandModel, int mileage, double dailyRentalPrice, double maintenanceCostPerKm, boolean available) {
        this.vehicleId = vehicleId;
        this.brandModel = brandModel;
        this.mileage = mileage;
        this.dailyRentalPrice = dailyRentalPrice;
        this.maintenanceCostPerKm = maintenanceCostPerKm;
        this.available = available;
    }

    // Getters: allow other classes to access private fields

    // Returns the vehicle's unique ID
    public String getVehicleId() {
        return vehicleId;
    }

    // Returns the brand and model
    public String getBrandModel() {
        return brandModel;
    }

    // Returns the mileage
    public int getMileage() {
        return mileage;
    }

    // Returns the daily rental price
    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    // Returns the maintenance cost per km
    public double getMaintenanceCostPerKm() {
        return maintenanceCostPerKm;
    }

    // Returns true if the vehicle is available, false otherwise
    public boolean isAvailable() {
        return available;
    }

    // Setter: allows other classes to update availability
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

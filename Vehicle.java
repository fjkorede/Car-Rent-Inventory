public class Vehicle {
    private String vehicleId;
    private String brandModel;
    private int mileage;
    private double dailyRentalPrice;
    private double maintenanceCostPerKm;
    private boolean available;

    // Constructor
    public Vehicle(String vehicleId, String brandModel, int mileage, double dailyRentalPrice, double maintenanceCostPerKm, boolean available) {
        this.vehicleId = vehicleId;
        this.brandModel = brandModel;
        this.mileage = mileage;
        this.dailyRentalPrice = dailyRentalPrice;
        this.maintenanceCostPerKm = maintenanceCostPerKm;
        this.available = available;
    }

    // Getters
    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public int getMileage() {
        return mileage;
    }

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public double getMaintenanceCostPerKm() {
        return maintenanceCostPerKm;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setter for availability
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

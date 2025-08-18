// The Vehicle class represents a single car in our system
public class Vehicle {
    // Properties (details about the vehicle)
    private String vehicleId;       // Unique ID (e.g., V1, V2)
    private String brandModel;      // Brand and Model (e.g., Toyota Corolla)
    private int mileage;            // Mileage in km
    private double dailyRentalPrice;// Price per day in dollars
    private boolean available;      // Whether the car is available or booked

    // Constructor (runs when we create a new Vehicle)
    public Vehicle(String vehicleId, String brandModel, int mileage, double dailyRentalPrice, boolean available) {
        this.vehicleId = vehicleId;
        this.brandModel = brandModel;
        this.mileage = mileage;
        this.dailyRentalPrice = dailyRentalPrice;
        this.available = available;
    }

    // Getter methods (to access vehicle details)
    public String getVehicleId() { return vehicleId; }
    public String getBrandModel() { return brandModel; }
    public int getMileage() { return mileage; }
    public double getDailyRentalPrice() { return dailyRentalPrice; }
    public boolean isAvailable() { return available; }

    // Setter method (to change availability)
    public void setAvailable(boolean available) { this.available = available; }
}

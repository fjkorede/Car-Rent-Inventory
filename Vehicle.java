// Vehicle class just stores info about one car
public class Vehicle {
    private String vehicleId;
    private String brandModel;
    private int mileage;
    private double dailyRentalPrice;
    private boolean available;
    private int bookedDays;   // how many days the customer booked

    // NEW maintenance fields
    private boolean needsMaintenance;
    private double maintenanceCost;

    // Constructor
    public Vehicle(String vehicleId, String brandModel, int mileage, double dailyRentalPrice, boolean available) {
        this.vehicleId = vehicleId;
        this.brandModel = brandModel;
        this.mileage = mileage;
        this.dailyRentalPrice = dailyRentalPrice;
        this.available = available;
        this.needsMaintenance = false;   // default
        this.maintenanceCost = 0.0;      // default
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

    public boolean isAvailable() { 
        return available; 
    }

    public boolean isNeedsMaintenance() { 
        return needsMaintenance; 
    }

    public double getMaintenanceCost() { 
        return maintenanceCost; 
    }

    public int getBookedDays() {
        return bookedDays;
    }

    // Setters
    public void setMileage(int mileage) { 
        this.mileage = mileage; 
    }

    public void setAvailable(boolean available) { 
        this.available = available; 
    }

    public void setNeedsMaintenance(boolean needsMaintenance) { 
        this.needsMaintenance = needsMaintenance; 
    }

    public void setMaintenanceCost(double maintenanceCost) { 
        this.maintenanceCost = maintenanceCost; 
    }

    public void setBookedDays(int bookedDays) {
        this.bookedDays = bookedDays;
    }
}

public class Transaction {
    private String customerName;
    private String vehicleId;
    private int rentalDays;
    private double revenue;
    private double extraCosts;

    // Constructor
    public Transaction(String customerName, String vehicleId, int rentalDays, double revenue, double extraCosts) {
        this.customerName = customerName;
        this.vehicleId = vehicleId;
        this.rentalDays = rentalDays;
        this.revenue = revenue;
        this.extraCosts = extraCosts;
    }

    // Show transaction nicely
    public String toString() {
        return "Customer: " + customerName +
               " | Vehicle ID: " + vehicleId +
               " | Days: " + rentalDays +
               " | Revenue: €" + revenue +
               " | Extra Costs: €" + extraCosts;
    }
}

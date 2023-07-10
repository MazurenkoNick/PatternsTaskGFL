package patterns.example.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class AmountAndRenterPoints {

    String customerName;
    Map<String, Double> amountsAndRenterPoints;

    private AmountAndRenterPoints() {}

    public AmountAndRenterPoints(String customerName, Map<String, Double> amountsAndRenterPoints) {
        this.customerName = customerName;
        this.amountsAndRenterPoints = amountsAndRenterPoints;
    }

    public AmountAndRenterPoints(String customerName) {
        this.customerName = customerName;
        this.amountsAndRenterPoints = new LinkedHashMap<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public Map<String, Double> getAmountsAndRenterPoints() {
        return amountsAndRenterPoints;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAmountsAndRenterPoints(Map<String, Double> amountsAndRenterPoints) {
        this.amountsAndRenterPoints = amountsAndRenterPoints;
    }

    public void add(String key, Double val) {
        amountsAndRenterPoints.put(key, val);
    }
}

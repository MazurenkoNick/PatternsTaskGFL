package patterns.example.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class AmountAndRenterPoints {

    Customer customer;
    Map<String, Double> amountsAndRenterPoints;

    private AmountAndRenterPoints() {}

    public AmountAndRenterPoints(Customer customer, Map<String, Double> amountsAndRenterPoints) {
        this.customer = customer;
        this.amountsAndRenterPoints = amountsAndRenterPoints;
    }

    public AmountAndRenterPoints(Customer customer) {
        this.customer = customer;
        this.amountsAndRenterPoints = new LinkedHashMap<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<String, Double> getAmountsAndRenterPoints() {
        return amountsAndRenterPoints;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAmountsAndRenterPoints(Map<String, Double> amountsAndRenterPoints) {
        this.amountsAndRenterPoints = amountsAndRenterPoints;
    }

    public void add(String key, Double val) {
        amountsAndRenterPoints.put(key, val);
    }
}

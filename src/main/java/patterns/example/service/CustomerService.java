package patterns.example.service;

import patterns.example.model.Customer;
import patterns.example.model.Rental;
import patterns.example.model.movie.NewMovie;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerService {

    public static final CustomerService INSTANCE = new CustomerService();
    private static final String TOTAL_AMOUNT = "Total amount";
    private static final String FREQUENT_RENTER_POINTS = "Frequent renter points";

    private CustomerService() {
    }

    public Map<String, Double> countAmountsAndRenterPoints(Customer customer) {
        Map<String, Double> customerAmountsAndRenterPoints = new LinkedHashMap<>();
        double totalAmount = 0;
        double frequentRenterPoints = 0;

        for (Rental rental : customer.getRentals()) {
            //determine amounts for each movie using `Strategy` pattern
            double currentAmount = countAmount(rental);
            customerAmountsAndRenterPoints.put(rental.getMovie().getTitle(), currentAmount);
            totalAmount += currentAmount;

            // add frequent renter points
            frequentRenterPoints += countFrequentRenterPoints(rental);
        }

        customerAmountsAndRenterPoints.put(TOTAL_AMOUNT, totalAmount);
        customerAmountsAndRenterPoints.put(FREQUENT_RENTER_POINTS, frequentRenterPoints);
        return customerAmountsAndRenterPoints;
    }

    private double countAmount(Rental rental) {
        int daysRented = rental.getDaysRented();
        return rental.getMovie().determineAmount(daysRented);
    }

    private int countFrequentRenterPoints(Rental rental) {
        int frequentRenterPoints = 1;
        // add bonus for a two-day new release rental
        if ((rental.getMovie() instanceof NewMovie) && rental.getDaysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}

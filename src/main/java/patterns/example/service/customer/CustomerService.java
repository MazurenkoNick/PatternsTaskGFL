package patterns.example.service.customer;

import patterns.example.model.AmountAndRenterPoints;
import patterns.example.model.Customer;
import patterns.example.model.Rental;
import patterns.example.model.movie.NewReleaseType;

public class CustomerService {

    public static final CustomerService INSTANCE = new CustomerService();
    private static final String TOTAL_AMOUNT = "Total amount";
    private static final String FREQUENT_RENTER_POINTS = "Frequent renter points";

    private CustomerService() {
    }

    public AmountAndRenterPoints countAmountsAndRenterPoints(Customer customer) {
        AmountAndRenterPoints amountAndRenterPoints = new AmountAndRenterPoints(customer.getName());
        double totalAmount = 0;
        double frequentRenterPoints = 0;

        for (Rental rental : customer.getRentals()) {
            //determine amounts for each movie using `Strategy` pattern
            double currentAmount = countAmount(rental);
            amountAndRenterPoints.add(rental.getMovie().getTitle(), currentAmount);
            totalAmount += currentAmount;

            // add frequent renter points
            frequentRenterPoints += countFrequentRenterPoints(rental);
        }

        amountAndRenterPoints.add(TOTAL_AMOUNT, totalAmount);
        amountAndRenterPoints.add(FREQUENT_RENTER_POINTS, frequentRenterPoints);
        return amountAndRenterPoints;
    }

    private double countAmount(Rental rental) {
        int daysRented = rental.getDaysRented();
        return rental.getMovie().determineAmount(daysRented);
    }

    private int countFrequentRenterPoints(Rental rental) {
        int frequentRenterPoints = 1;
        // add bonus for a two-day new release rental
        if ((rental.getMovie().getMovieType() instanceof NewReleaseType) && rental.getDaysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}

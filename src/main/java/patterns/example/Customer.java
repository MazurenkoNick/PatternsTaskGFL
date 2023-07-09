package patterns.example;

import patterns.example.model.movie.NewMovie;

import java.util.List;

@SuppressWarnings("StringConcatenationInLoop")
class Customer {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name, List<Rental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Rental each : rentals) {
            double thisAmount = 0;

            //determine amounts for each line using `Strategy` pattern
            int daysRented = each.getDaysRented();
            thisAmount += each.getMovie().determineAmount(daysRented);

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie() instanceof NewMovie) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
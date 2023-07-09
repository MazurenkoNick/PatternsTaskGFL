package patterns.example.model;

import java.util.List;
import java.util.Set;

public class Customer {
    private final String name;
    private final Set<Rental> rentals;

    public Customer(String name, Set<Rental> rentals) {
        this.name = name;
        if (rentals == null) {
            throw new IllegalArgumentException("Rentals can't be null");
        }
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public Set<Rental> getRentals() {
        return rentals;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void addAllRentals(List<Rental> rentals) {
        for (Rental r: rentals) {
            addRental(r);
        }
    }
}
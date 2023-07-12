package patterns.example.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer {
    private String name;
    private Set<Rental> rentals;

    public Customer() {}

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

    public void setName(String name) {
        this.name = name;
    }

    public void setRentals(Set<Rental> rentals) {
        if (rentals == null) {
            throw new IllegalArgumentException("Rentals can't be null");
        }
        this.rentals = rentals;

    }

    public void addRental(Rental rental) {
        if (rentals == null) {
            rentals = new HashSet<>();
        }
        rentals.add(rental);
    }

    public void addAllRentals(List<Rental> rentals) {
        for (Rental r: rentals) {
            addRental(r);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", rentals=" + rentals +
                '}';
    }
}
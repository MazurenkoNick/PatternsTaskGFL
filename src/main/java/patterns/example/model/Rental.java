package patterns.example.model;

import patterns.example.model.movie.Movie;

import java.util.Objects;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rental rental)) return false;
        return daysRented == rental.daysRented && Objects.equals(movie, rental.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, daysRented);
    }
}

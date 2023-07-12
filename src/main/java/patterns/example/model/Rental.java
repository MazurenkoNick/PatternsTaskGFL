package patterns.example.model;

import patterns.example.model.movie.Movie;

import java.util.Objects;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental() {}

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "movie=" + movie +
                ", daysRented=" + daysRented +
                '}';
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

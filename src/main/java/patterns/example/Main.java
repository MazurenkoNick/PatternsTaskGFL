package patterns.example;

import patterns.example.model.Customer;
import patterns.example.model.Rental;
import patterns.example.model.movie.ChildrenMovie;
import patterns.example.model.movie.Movie;
import patterns.example.model.movie.NewMovie;
import patterns.example.model.movie.RegularMovie;
import patterns.example.service.HtmlCustomerStatementConverter;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Movie m1 = RegularMovie.builder()
                .addTitle("Rambo")
                .addCountry("USA")
                .addDirector("Sylvester Stallone")
                .addDescription("Rambo is an American media franchise centered on a " +
                        "series of action films featuring John J. Rambo.")
                .build();

        Movie m2 = NewMovie.builder()
                .addTitle("Lord of the Rings")
                .addCountry("New Zealand")
                .addDirector("Peter Jackson")
                .addDescription("Based on the novel The Lord of the Rings by J. R. R. Tolkien")
                .build();

        Movie m3 = ChildrenMovie.builder()
                .addTitle("Harry Potter")
                .addCountry("Britain")
                .addDirector("Chris Columbus")
                .addMinimumAge(18)
                .addDescription("Harry Potter is a film series based on the eponymous novels by J. K. Rowling.")
                .build();

        Set<Rental> rentals = Set.of(new Rental(m1, 1),
                new Rental(m2, 4),
                new Rental(m3, 5));

        Customer customer = new Customer("John Doe", rentals);
        String statement = HtmlCustomerStatementConverter.INSTANCE.getStatement(customer);

        System.out.println(statement);
    }
}
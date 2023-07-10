package patterns.example;

import patterns.example.model.Customer;
import patterns.example.model.Rental;
import patterns.example.model.movie.ChildrenType;
import patterns.example.model.movie.Movie;
import patterns.example.model.movie.NewReleaseType;
import patterns.example.model.movie.RegularType;
import patterns.example.service.converter.HtmlCustomerStatementConverter;
import patterns.example.service.file.RentalJsonOperator;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Movie m1 = Movie.builder()
                .addMovieType(RegularType.INSTANCE)
                .addTitle("Rambo")
                .addCountry("USA")
                .addDirector("Sylvester Stallone")
                .addDescription("Rambo is an American media franchise centered on a " +
                        "series of action films featuring John J. Rambo.")
                .build();

        Movie m2 = Movie.builder()
                .addMovieType(NewReleaseType.INSTANCE)
                .addTitle("Lord of the Rings")
                .addCountry("New Zealand")
                .addDirector("Peter Jackson")
                .addDescription("Based on the novel The Lord of the Rings by J. R. R. Tolkien")
                .build();

        Movie m3 = Movie.builder()
                .addMovieType(ChildrenType.INSTANCE)
                .addTitle("Harry Potter")
                .addCountry("Britain")
                .addDirector("Chris Columbus")
                .addMinimumAge(18)
                .addDescription("Harry Potter is a film series based on the eponymous novels by J. K. Rowling.")
                .addActors(List.of("Daniel Radcliffe", "Rupert Grint", "Emma Watson"))
                .build();

        Set<Rental> rentals = Set.of(new Rental(m1, 1),
                new Rental(m2, 4),
                new Rental(m3, 5));

        Customer customer = new Customer("John Doe", rentals);
        String statement = HtmlCustomerStatementConverter.INSTANCE.getStatement(customer);

        System.out.println(statement);

        /// files manipulations
        RentalJsonOperator.INSTANCE.updateUserAmountAndRenterPoints(customer);
        RentalJsonOperator.INSTANCE.updateUserAmountAndRenterPoints(customer); // check that info will be rewritten
        System.out.println(RentalJsonOperator.INSTANCE.readAmountAndRenterPointsToInstance(customer.getName()));

        RentalJsonOperator.INSTANCE.addMovie(m1); // todo: convert movies.json into a proper json list
        RentalJsonOperator.INSTANCE.addMovie(m2); // check that movie list will not be rewritten
        RentalJsonOperator.INSTANCE.addMovie(m3);

        List<Movie> movies = RentalJsonOperator.INSTANCE.readMoviesToList();
        movies.forEach(System.out::println);
        RentalJsonOperator.INSTANCE.addAllMovies(movies);

        // todo: make menu in the console
    }
}
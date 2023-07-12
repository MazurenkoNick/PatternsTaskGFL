package patterns.example;

import patterns.example.model.Customer;
import patterns.example.model.Rental;
import patterns.example.model.movie.*;
import patterns.example.service.converter.CustomerStatementConverter;
import patterns.example.service.converter.DefaultCustomerStatementConverter;
import patterns.example.service.converter.HtmlCustomerStatementConverter;
import patterns.example.service.file.RentalFileOperator;
import patterns.example.service.file.RentalJsonOperator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class Menu {

    public static final Menu INSTANCE = new Menu();

    private static final String MOVIE_LIST = "/movieList";
    private static final String QUIT = "/q";
    private static final String ADD_MOVIE = "/addMovie";
    private static final String FIND_MOVIE = "/findMovie";
    private static final String ADD_CUSTOMER_RENTALS = "/addCustomerRentals";
    private static final String FIND_CUSTOMER_RENTALS = "/findCustomerRentals";
    private static final String COUNT_RENTER_POINTS = "/countRenterPoints";
    private static final String NEW_TYPE = "new";
    private static final String CHILDREN_TYPE = "children";
    private static final String REGULAR_TYPE = "regular";
    private static final String FIND_BY_TITLE = "/findByTitle";
    private static final String FIND_BY_COUNTRY = "/findByCountry";
    private static final String FIND_BY_DIRECTOR = "/findByDirector";
    private static final String FIND_BY_ACTOR = "/findByActor";
    private static final String DEFAULT_FORMAT = "/default";
    private static final String HTML_FORMAT = "/html";
    private static final Scanner sc = new Scanner(System.in);
    private final RentalFileOperator rentalFileOperator;
    private final DefaultCustomerStatementConverter defaultStatementConverter;
    private final HtmlCustomerStatementConverter htmlStatementConverter;

    private Menu() {
        this.rentalFileOperator = RentalJsonOperator.INSTANCE;
        this.defaultStatementConverter = DefaultCustomerStatementConverter.INSTANCE;
        this.htmlStatementConverter = HtmlCustomerStatementConverter.INSTANCE;
    }

    public void menu() {
        String action = "";

        printInstruction();
        while (!action.equalsIgnoreCase(QUIT)) {
            action = sc.nextLine();
            if (action.equalsIgnoreCase(MOVIE_LIST)) {
                printMovieList();
            }
            else if (action.equalsIgnoreCase(ADD_MOVIE)) {
                addMovie();
            }
            else if (action.equalsIgnoreCase(FIND_MOVIE)) {
                findMovie();
            }
            else if (action.equalsIgnoreCase(ADD_CUSTOMER_RENTALS)) {
                addCustomerAndRentals();
            }
            else if (action.equalsIgnoreCase(FIND_CUSTOMER_RENTALS)) {
                findCustomerAndRentals();
            }
            else if (action.equalsIgnoreCase(COUNT_RENTER_POINTS)) {
                countRenterPoints();
            }
            printInstruction();
        }

        sc.close();
    }

    private void printInstruction() {
        System.out.println("\nTo close the program print: " + QUIT);
        System.out.println("To get a list of available movies print: " + MOVIE_LIST);
        System.out.println("To add a new movie print: " + ADD_MOVIE);
        System.out.println("To find a movie by some property print: " + FIND_MOVIE);
        System.out.println("To add a new customer and his rentals print: " + ADD_CUSTOMER_RENTALS);
        System.out.println("To find customer and his rentals print: " + FIND_CUSTOMER_RENTALS);
        System.out.println("Count user's amount and renter points: " + COUNT_RENTER_POINTS);
        System.out.println();
    }

    private void findCustomerAndRentals() {
        System.out.println("Print name of the customer: ");
        List<Customer> customers = rentalFileOperator.readCustomerRentalsToList();
        String name = sc.nextLine();

        customers.stream()
                .filter(Objects::nonNull)
                .filter(c -> name.equals(c.getName()))
                .forEach(System.out::println);
    }

    private void countRenterPoints() {
        System.out.println("Print name of the customer: ");
        List<Customer> customers = rentalFileOperator.readCustomerRentalsToList();
        String name = sc.nextLine();

        System.out.printf(
                "Renter points has just been counted. Print format (%s or %s)\n",
                DEFAULT_FORMAT,
                HTML_FORMAT
        );

        String format = sc.nextLine();
        CustomerStatementConverter statementConverter = retrieveStatementConverterType(format);

        if (statementConverter == null) {
            System.out.println("Wrong format type");
        }
        else {
            customers.stream()
                    .filter(Objects::nonNull)
                    .filter(c -> name.equals(c.getName()))
                    .map(statementConverter::getStatement)
                    .forEach(System.out::println);
        }
    }

    private void addCustomerAndRentals() {
        Customer customer = new Customer();
        List<Movie> availableMovies = rentalFileOperator.readMoviesToList();

        if (availableMovies == null || availableMovies.isEmpty()) {
            System.out.println("There are no movies");
        }
        else {
            System.out.println("Print customer name:");
            customer.setName(sc.nextLine());
            String action = "";

            while (!action.equalsIgnoreCase(QUIT)) {
                System.out.println("Add all available movies and rental days for this customer");
                System.out.println("Print movie title:");

                String title = sc.nextLine();
                System.out.println("Print rental days");
                int rentalDays = Integer.parseInt(sc.nextLine());

                Optional<Movie> m = availableMovies.stream()
                        .filter(Objects::nonNull)
                        .filter(mov -> mov.getTitle().equals(title))
                        .findFirst();

                if (m.isPresent()) {
                    Rental rental = new Rental(m.get(), rentalDays);
                    customer.addRental(rental);
                }
                else {
                    System.out.println("There is no movie with this title");
                }
                System.out.println("If you want to finish adding customer rentals, print: " + QUIT);
                System.out.println("If you want to proceed, print anything else");
                action = sc.nextLine();
            }
        }

        rentalFileOperator.addCustomer(customer);
        System.out.println(customer.getName() + " added!");
    }

    private void findMovie() {
        List<Movie> movies = rentalFileOperator.readMoviesToList();

        System.out.printf("print: %s or %s or %s or %s\n",
                FIND_BY_TITLE, FIND_BY_COUNTRY, FIND_BY_DIRECTOR, FIND_BY_ACTOR);
        String action = sc.nextLine();
        // predicate will be changed in the one of the if statements
        Predicate<Movie> predicate = movie -> false;

        if (action.equalsIgnoreCase(FIND_BY_TITLE)) {
            System.out.println("Print title:");
            String title = sc.nextLine();
            predicate = m -> m.getTitle().equals(title);
        }
        else if (action.equalsIgnoreCase(FIND_BY_COUNTRY)) {
            System.out.println("Print country:");
            String country = sc.nextLine();
            predicate = m -> m.getCountry().equals(country);
        }
        else if (action.equalsIgnoreCase(FIND_BY_DIRECTOR)) {
            System.out.println("Print director:");
            String director = sc.nextLine();
            predicate = m -> m.getDirector().equals(director);
        }
        else if (action.equalsIgnoreCase(FIND_BY_ACTOR)) {
            System.out.println("Print actor:");
            String actor = sc.nextLine();
            predicate = m -> m.getActors().contains(actor);
        }

        movies.stream()
                .filter(Objects::nonNull)
                .filter(predicate)
                .forEach(System.out::println);
    }

    private void printMovieList() {
        List<Movie> movies = rentalFileOperator.readMoviesToList();
        if (movies != null && !movies.isEmpty()) {
            movies.forEach(System.out::println);
        } else {
            System.out.println("There are no movies");
        }
    }

    private void addMovie() {
        Movie m = buildMovie();
        rentalFileOperator.addMovie(m);
        System.out.println("Movie added");
    }

    private Movie buildMovie() {
        System.out.println("Print movie title: ");
        String title = sc.nextLine();
        System.out.printf("Print movie type (%s, %s or %s): \n", NEW_TYPE, REGULAR_TYPE, CHILDREN_TYPE);
        MovieType type = retrieveMovieType(sc.nextLine());
        System.out.println("Print movie description:");
        String description = sc.nextLine();
        System.out.println("Print country of origin of the movie:");
        String country = sc.nextLine();
        System.out.println("Print movie director:");
        String director = sc.nextLine();
        System.out.println("Print minimum age allowed to watch the movie:");
        String minimumAge = sc.nextLine();
        if (minimumAge.equals("")) {
            minimumAge = "0";
        }
        System.out.println("Print actors using comma separator, e.g. `Michael Jackson, Kim, Ivan Ivanenko`:");
        List<String> actors = List.of(sc.nextLine().split(","));

        return Movie.builder()
                .addTitle(title)
                .addMovieType(type)
                .addDescription(description)
                .addCountry(country)
                .addDirector(director)
                .addMinimumAge(Integer.parseInt(minimumAge))
                .addActors(actors)
                .build();
    }

    private MovieType retrieveMovieType(String s) {
        MovieType type = null;
        if (CHILDREN_TYPE.equalsIgnoreCase(s)) {
            type = ChildrenType.INSTANCE;
        }
        else if (NEW_TYPE.equalsIgnoreCase(s)) {
            type = NewReleaseType.INSTANCE;
        }
        else if (REGULAR_TYPE.equalsIgnoreCase(s)) {
            type = RegularType.INSTANCE;
        }
        return type;
    }

    private CustomerStatementConverter retrieveStatementConverterType(String format) {
        CustomerStatementConverter converter = null;
        if (DEFAULT_FORMAT.equals(format)) {
            converter = defaultStatementConverter;
        }
        else if (HTML_FORMAT.equals(format)) {
            converter = htmlStatementConverter;
        }
        return converter;
    }
}

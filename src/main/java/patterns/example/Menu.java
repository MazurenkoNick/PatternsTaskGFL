package patterns.example;

import patterns.example.model.movie.*;
import patterns.example.service.file.RentalFileOperator;
import patterns.example.service.file.RentalJsonOperator;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Menu {

    public static final Menu INSTANCE = new Menu();

    private static final String MOVIE_LIST = "/movieList";
    private static final String QUIT = "/q";
    private static final String ADD_MOVIE = "/addMovie";
    private static final String FIND_MOVIE = "/findMovie";
    private static final String NEW_TYPE = "new";
    private static final String CHILDREN_TYPE = "children";
    private static final String REGULAR_TYPE = "regular";
    private static final String FIND_BY_TITLE = "/findByTitle";
    private static final String FIND_BY_COUNTRY = "/findByCountry";
    private static final String FIND_BY_DIRECTOR = "/findByDirector";
    private static final String FIND_BY_ACTOR = "/findByActor";
    private final RentalFileOperator rentalFileOperator;

    public Menu() {
        this.rentalFileOperator = RentalJsonOperator.INSTANCE;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
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
            else if (action.equalsIgnoreCase("/findMovie")) {
                findMovie();
            }
            printInstruction();
        }

        sc.close();
    }

    private void printInstruction() {
        System.out.println("\nTo close the program print: " + QUIT);
        System.out.println("To get a list of movies print: " + MOVIE_LIST);
        System.out.println("To add a new movie print: " + ADD_MOVIE);
        System.out.println("To find the movie by name print: " + FIND_MOVIE);
        System.out.println();
    }

    private void findMovie() {
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
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
        if (s.equalsIgnoreCase(CHILDREN_TYPE)) {
            type = ChildrenType.INSTANCE;
        }
        else if (s.equalsIgnoreCase(NEW_TYPE)) {
            type = NewReleaseType.INSTANCE;
        }
        else if (s.equalsIgnoreCase(REGULAR_TYPE)) {
            type = RegularType.INSTANCE;
        }
        return type;
    }
}

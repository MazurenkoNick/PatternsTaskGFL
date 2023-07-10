package patterns.example.service.file;

import patterns.example.model.AmountAndRenterPoints;
import patterns.example.model.Customer;
import patterns.example.model.movie.Movie;
import patterns.example.service.customer.CustomerService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class RentalFileOperator {

    private final FileOperator fileOperator;
    private final CustomerService customerService;

    RentalFileOperator() {
        this.fileOperator = FileOperator.INSTANCE;
        this.customerService = CustomerService.INSTANCE;
    }

    public abstract String getMovieFilePath();

    protected abstract String getFilePackagePrefix();

    protected abstract String getFilePackagePostfix();

    // factory method
    protected abstract Mapper getMapper();

    public void addAllMovies(List<Movie> movies) {
        createMovieFile();
        for (Movie movie: movies) {
            add(movie);
        }
    }

    public void addMovie(Movie movie) {
        createMovieFile();
        add(movie);
    }

    public String readMoviesToString() {
        String movieFilePath = getMovieFilePath();
        return fileOperator.readFile(movieFilePath);
    }

    public List<Movie> readMoviesToList() {
        File file = new File(getMovieFilePath());
        List<Movie> movies = new ArrayList<>();

        try (Scanner dataReader = new Scanner(file)) {
            while (dataReader.hasNextLine()) {
                String line = dataReader.nextLine();
                movies.add(getMapper().getInstanceFromString(line, Movie.class));
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("Exception Occurred");
        }

        return movies;
    }

    public void updateUserAmountAndRenterPoints(Customer customer) {
        String customerInfoFilePath = getFilePackagePrefix() + customer.getName() + getFilePackagePostfix();
        // remove content if possible
        fileOperator.createFileIfNotExist(customerInfoFilePath);
        fileOperator.removeContent(customerInfoFilePath);

        AmountAndRenterPoints userInfo = customerService.countAmountsAndRenterPoints(customer);
        String formattedUserInfo = getMapper().getStringFromInstance(userInfo);
        fileOperator.appendText(customerInfoFilePath, formattedUserInfo);
    }

    public String readAmountAndRenterPointsToString(String username) {
        String filePath = getFilePackagePrefix() + username + getFilePackagePostfix();
        File file = new File(filePath);

        if (file.exists() && !file.isDirectory()) {
            return fileOperator.readFile(filePath);
        }
        return null;
    }

    public AmountAndRenterPoints readAmountAndRenterPointsToInstance(String username) {
        String result = readAmountAndRenterPointsToString(username);
        return getMapper().getInstanceFromString(result, AmountAndRenterPoints.class);
    }

    private void add(Movie movie) {
        String formattedMovie = getMapper().getStringFromInstance(movie);
        fileOperator.appendText(getMovieFilePath(), formattedMovie);
    }

    private void createMovieFile() {
        String movieFilePath = getMovieFilePath();
        fileOperator.createFileIfNotExist(movieFilePath);
    }
}

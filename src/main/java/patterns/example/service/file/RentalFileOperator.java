package patterns.example.service.file;

import com.fasterxml.jackson.core.type.TypeReference;
import patterns.example.model.AmountAndRenterPoints;
import patterns.example.model.Customer;
import patterns.example.model.movie.Movie;
import patterns.example.service.customer.CustomerService;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
        boolean empty = createMovieFile();
        List<Movie> allMovies = new LinkedList<>(movies);

        if (!empty) {
            allMovies.addAll(readMoviesToList());
            fileOperator.removeContentIfExist(getMovieFilePath());
        }
        fileOperator.appendText(getMovieFilePath(), getMapper().getStringFromInstance(allMovies));
    }

    public void addMovie(Movie movie) {
        addAllMovies(List.of(movie));
    }

    public String readMoviesToString() {
        String movieFilePath = getMovieFilePath();
        return fileOperator.readFile(movieFilePath);
    }

    public List<Movie> readMoviesToList() {
        createMovieFile();
        String result = readMoviesToString();
        List<Movie> list = getMapper().getInstanceFromString(result, new TypeReference<List<Movie>>() {});
        // return empty list, if there is no list in the file
        return Objects.requireNonNullElseGet(list, LinkedList::new);
    }

    public void updateUserAmountAndRenterPoints(Customer customer) {
        String customerInfoFilePath = getFilePackagePrefix() + customer.getName() + getFilePackagePostfix();
        // remove content if possible
        fileOperator.createFileIfNotExist(customerInfoFilePath);
        fileOperator.removeContentIfExist(customerInfoFilePath);

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

    private boolean createMovieFile() {
        String movieFilePath = getMovieFilePath();
        return fileOperator.createFileIfNotExist(movieFilePath);
    }
}

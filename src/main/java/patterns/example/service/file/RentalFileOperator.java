package patterns.example.service.file;

import com.fasterxml.jackson.core.type.TypeReference;
import patterns.example.model.Customer;
import patterns.example.model.movie.Movie;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public abstract class RentalFileOperator {

    private final FileOperator fileOperator;

    RentalFileOperator() {
        this.fileOperator = FileOperator.INSTANCE;
    }

    public abstract String getMovieFilePath();
    public abstract String getCustomerRentalsFilePath();
    protected abstract Mapper getMapper(); // factory method

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
        return readFileToString(getMovieFilePath());
    }

    public List<Movie> readMoviesToList() {
        String text = readMoviesToString();
        // return empty list, if there is no list in the file
        return readListFromString(text, new TypeReference<List<Movie>>() {});
    }

    public void addAllCustomers(List<Customer> customers) {
        boolean empty = createCustomerRentalsFile();
        List<Customer> allCustomers = new LinkedList<>(customers);

        if (!empty) {
            allCustomers.addAll(readCustomerRentalsToList());
            fileOperator.removeContentIfExist(getCustomerRentalsFilePath());
        }
        fileOperator.appendText(getCustomerRentalsFilePath(), getMapper().getStringFromInstance(allCustomers));
    }

    public void addCustomer(Customer customer) {
        addAllCustomers(List.of(customer));
    }

    public String readCustomersToString() {
        return readFileToString(getCustomerRentalsFilePath());
    }

    public List<Customer> readCustomerRentalsToList() {
        String text = readCustomersToString();
        // return empty list, if there is no list in the file
        return readListFromString(text, new TypeReference<List<Customer>>() {});
    }

    private <T> List<T> readListFromString(String text, TypeReference<List<T>> listTypeReference) {
        if (text == null) {
            return new LinkedList<>();
        }
        return getMapper().getInstanceFromString(text, listTypeReference);
    }

    private String readFileToString(String filePath) {
        File file = new File(filePath);

        if (file.exists() && !file.isDirectory()) {
            return fileOperator.readFile(filePath);
        }
        return null;
    }

    private boolean createCustomerRentalsFile() {
        String rentalFilePath = getCustomerRentalsFilePath();
        return fileOperator.createFileIfNotExist(rentalFilePath);
    }

    private boolean createMovieFile() {
        String movieFilePath = getMovieFilePath();
        return fileOperator.createFileIfNotExist(movieFilePath);
    }
}

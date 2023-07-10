package patterns.example.service.file;

import patterns.example.model.movie.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class RentalFileOperator {

    private final FileOperator fileOperator;

    RentalFileOperator() {
        this.fileOperator = FileOperator.INSTANCE;
    }

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
            /*
            TODO: create iterator in each `RentalFileOperator`
                implementation to properly read movies from file. Not line by line
            */
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

    private void add(Movie movie) {
        String formattedMovie = getMapper().format(movie);
        fileOperator.appendText(getMovieFilePath(), formattedMovie);
    }

    private void createMovieFile() {
        String movieFilePath = getMovieFilePath();
        fileOperator.createFileIfNotExist(movieFilePath);
    }

    public abstract String getMovieFilePath();

    // factory method
    public abstract Mapper getMapper();
}

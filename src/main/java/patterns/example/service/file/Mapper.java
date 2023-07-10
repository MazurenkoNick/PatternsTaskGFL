package patterns.example.service.file;

import patterns.example.model.movie.Movie;

public interface Mapper {

    <T> T getInstanceFromString(String json, Class<T> cls);

    String format(Movie movie);
}

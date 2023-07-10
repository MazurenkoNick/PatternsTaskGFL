package patterns.example.model.movie;

import java.util.List;

public class Movie {

    private String title;
    private String country;
    private String description;
    private String director; // todo: create an actor & director POJOs
    private List<String> actors;
    private int minimumAge;
    private MovieType movieType;

    private Movie() {}

    private Movie(MovieBuilder builder) {
        this.title = builder.title;
        this.country = builder.country;
        this.description = builder.description;
        this.director = builder.director;
        this.actors = builder.actors;
        this.movieType = builder.movieType;
        this.minimumAge = builder.minimumAge;
    }

    public static class MovieBuilder {
        private String title;
        private String country;
        private String description;
        private String director; // todo: create an actor & director POJOs
        private List<String> actors;
        private int minimumAge;
        private MovieType movieType;


        public MovieBuilder addTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder addCountry(String country) {
            this.country = country;
            return this;
        }

        public MovieBuilder addDescription(String description) {
            this.description = description;
            return this;
        }

        public MovieBuilder addDirector(String director) {
            this.director = director;
            return this;
        }

        public MovieBuilder addActors(List<String> actors) {
            this.actors = actors;
            return this;
        }

        public MovieBuilder addMovieType(MovieType movieType) {
            this.movieType = movieType;
            return this;
        }

        public MovieBuilder addMinimumAge(int minimumAge) {
            this.minimumAge = minimumAge;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    public double determineAmount(int daysRented) {
        return movieType.determineAmount(daysRented);
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                ", minimumAge=" + minimumAge +
                ", movieType=" + movieType +
                '}';
    }
}

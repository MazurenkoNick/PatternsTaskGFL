package patterns.example.model.movie;

import java.util.List;

public abstract class Movie {

    private final String title;
    private final String country;
    private final String description;
    private final String director; // todo: create an actor & director POJOs
    private final List<String> actors;

    Movie(MovieBuilder<?> builder) {
        this.title = builder.title;
        this.country = builder.country;
        this.description = builder.description;
        this.director = builder.director;
        this.actors = builder.actors;
    }

    // Using a parallel hierarchy of builders, each nested in the corresponding implementation
    abstract static class MovieBuilder<T extends MovieBuilder<T>> {
        private String title;
        private String country;
        private String description;
        private String director; // todo: create an actor & director POJOs
        private List<String> actors;

        public T addTitle(String title) {
            this.title = title;
            return self();
        }

        public T addCountry(String country) {
            this.country = country;
            return self();
        }

        public T addDescription(String description) {
            this.description = description;
            return self();
        }

        public T addDirector(String director) {
            this.director = director;
            return self();
        }

        public T addActors(List<String> actors) {
            this.actors = actors;
            return self();
        }

        public abstract Movie build();

        // Subclasses must override this method to return "this"
        protected abstract T self();
    }

    public abstract double determineAmount(int daysRented);

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
}

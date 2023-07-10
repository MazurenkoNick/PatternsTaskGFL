package patterns.example.model.movie;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChildrenMovie.class, name = "children"),
        @JsonSubTypes.Type(value = RegularMovie.class, name = "regular"),
        @JsonSubTypes.Type(value = NewMovie.class, name = "new")
})
public abstract class Movie {

    private String title;
    private String country;
    private String description;
    private String director; // todo: create an actor & director POJOs
    private List<String> actors;

    Movie() {}

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
                '}';
    }
}

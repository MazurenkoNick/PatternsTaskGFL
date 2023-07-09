package patterns.example.model.movie;

public class NewMovie extends Movie {

    private NewMovie(NewMovieBuilder builder) {
        super(builder);
    }

    public static class NewMovieBuilder extends MovieBuilder<NewMovieBuilder> {

        @Override
        public NewMovie build() {
            return new NewMovie(this);
        }

        @Override
        protected NewMovieBuilder self() {
            return this;
        }
    }

    public static NewMovieBuilder builder() {
        return new NewMovieBuilder();
    }

    @Override
    public double determineAmount(int daysRented) {
        return daysRented * 3;
    }
}

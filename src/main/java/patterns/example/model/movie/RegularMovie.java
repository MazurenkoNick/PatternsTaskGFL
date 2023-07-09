package patterns.example.model.movie;

public class RegularMovie extends Movie {

    private RegularMovie(RegularMovieBuilder builder) {
        super(builder);
    }

    public static class RegularMovieBuilder extends MovieBuilder<RegularMovieBuilder> {

        @Override
        public RegularMovie build() {
            return new RegularMovie(this);
        }

        @Override
        protected RegularMovieBuilder self() {
            return this;
        }
    }

    public static RegularMovieBuilder builder() {
        return new RegularMovieBuilder();
    }

    @Override
    public double determineAmount(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }
}

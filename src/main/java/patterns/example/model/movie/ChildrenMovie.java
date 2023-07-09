package patterns.example.model.movie;

public class ChildrenMovie extends Movie {

    private ChildrenMovie(ChildrenMovieBuilder builder) {
        super(builder);
    }

    public static class ChildrenMovieBuilder extends MovieBuilder<ChildrenMovieBuilder> {

        @Override
        public ChildrenMovie build() {
            return new ChildrenMovie(this);
        }

        @Override
        protected ChildrenMovieBuilder self() {
            return this;
        }
    }

    public static ChildrenMovieBuilder builder() {
        return new ChildrenMovieBuilder();
    }

    @Override
    public double determineAmount(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented - 3) * 1.5;
        return amount;
    }
}

package patterns.example.model.movie;

public class ChildrenMovie extends Movie {

    private final int minimumAge;

    private ChildrenMovie(ChildrenMovieBuilder builder) {
        super(builder);
        this.minimumAge = builder.minimumAge;
    }

    public static class ChildrenMovieBuilder extends MovieBuilder<ChildrenMovieBuilder> {
        // can add additional fields in the Movie's implementations if we need it in the future, e.g.:
        private int minimumAge = 0;

        public ChildrenMovieBuilder addMinimumAge(int age) {
            this.minimumAge = age;
            return self();
        }

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

    public int getMinimumAge() {
        return minimumAge;
    }
}

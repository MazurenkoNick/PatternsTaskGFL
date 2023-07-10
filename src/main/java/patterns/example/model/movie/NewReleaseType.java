package patterns.example.model.movie;

public class NewReleaseType implements MovieType {

    public static final NewReleaseType INSTANCE = new NewReleaseType();

    private NewReleaseType() {}

    @Override
    public double determineAmount(int daysRented) {
        return daysRented * 3;
    }
}

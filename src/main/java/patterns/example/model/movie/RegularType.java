package patterns.example.model.movie;

public class RegularType implements MovieType {

    public static final RegularType INSTANCE = new RegularType();

    private RegularType() {}

    @Override
    public double determineAmount(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }
}

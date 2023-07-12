package patterns.example.model.movie;

public class ChildrenType implements MovieType {

    public static final ChildrenType INSTANCE = new ChildrenType();

    private ChildrenType() {
    }

    @Override
    public double determineAmount(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented - 3) * 1.5;
        return amount;
    }

    @Override
    public String toString() {
        return "ChildrenType";
    }
}

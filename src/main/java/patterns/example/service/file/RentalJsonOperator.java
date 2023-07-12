package patterns.example.service.file;

public class RentalJsonOperator extends RentalFileOperator {

    public static final RentalJsonOperator INSTANCE = new RentalJsonOperator();
    private static final String FILE_PACKAGE_PREFIX = "data/";
    private static final String MOVIE_FILE_NAME = "movies";
    private static final String RENTAL_FILE_NAME = "rentals";
    private static final String POSTFIX = ".json";
    private final JsonMapper jsonMapper;

    private RentalJsonOperator() {
        super();
        this.jsonMapper = JsonMapper.INSTANCE;
    }

    @Override
    public String getCustomerRentalsFilePath() {
        return FILE_PACKAGE_PREFIX + RENTAL_FILE_NAME + POSTFIX;
    }

    @Override
    public String getMovieFilePath() {
        return FILE_PACKAGE_PREFIX + MOVIE_FILE_NAME + POSTFIX;
    }

    @Override
    protected Mapper getMapper() {
        return jsonMapper;
    }
}

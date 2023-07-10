package patterns.example.service.file;

public class RentalJsonOperator extends RentalFileOperator {

    public static final RentalJsonOperator INSTANCE = new RentalJsonOperator();
    private static final String FILE_PACKAGE_PREFIX = "data/";
    private static final String MOVIE_FILE_NAME = "movies.txt";
    private final JsonMapper jsonMapper;

    private RentalJsonOperator() {
        super();
        this.jsonMapper = JsonMapper.INSTANCE;
    }

    @Override
    public String getMovieFilePath() {
        return FILE_PACKAGE_PREFIX + MOVIE_FILE_NAME;
    }

    @Override
    public Mapper getMapper() {
        return jsonMapper;
    }
}

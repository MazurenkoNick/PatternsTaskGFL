package patterns.example.model.movie;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChildrenType.class, name = "Children"),
        @JsonSubTypes.Type(value = RegularType.class, name = "Regular"),
        @JsonSubTypes.Type(value = NewReleaseType.class, name = "New")
})
public interface MovieType {

    double determineAmount(int daysRented);
}

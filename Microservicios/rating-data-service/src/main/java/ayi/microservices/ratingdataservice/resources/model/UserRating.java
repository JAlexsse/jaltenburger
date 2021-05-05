package ayi.microservices.ratingdataservice.resources.model;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRating {
    private List<RatingData> userRatings;
}

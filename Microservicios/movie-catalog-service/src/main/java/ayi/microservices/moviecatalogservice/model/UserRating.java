package ayi.microservices.moviecatalogservice.model;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRating {
    private List<RatingData> userRatings;
}

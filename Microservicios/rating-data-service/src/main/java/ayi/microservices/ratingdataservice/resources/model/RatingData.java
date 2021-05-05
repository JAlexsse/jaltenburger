package ayi.microservices.ratingdataservice.resources.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingData {

    private int movieId;
    private int rating;
    
}

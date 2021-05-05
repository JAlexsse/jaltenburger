package ayi.microservices.movieinfoservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    private int movieId;
    private String title;
    private String description;
}

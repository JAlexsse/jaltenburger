package ayi.microservices.moviecatalogservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDetails {
    private int movieId;
    private String title;
    private String description;
}

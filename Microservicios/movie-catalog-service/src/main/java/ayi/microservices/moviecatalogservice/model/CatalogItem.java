package ayi.microservices.moviecatalogservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogItem {
    private String title;
    private String description;
    private int rating;
}

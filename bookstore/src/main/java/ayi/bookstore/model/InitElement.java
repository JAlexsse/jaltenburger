package ayi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InitElement {

    @JsonProperty("name")
    private String name;

    @JsonProperty("author")
    private int author_id;

    @JsonProperty("publishing")
    private int publishing_id;



    
}

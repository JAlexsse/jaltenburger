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

    @JsonProperty("book_id")
    private int book_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("publishing")
    private int publishing_id;

    @JsonProperty("publishingname")
    private String publishing_name;

    @JsonProperty("authorname")
    private String author_name;

    @JsonProperty("author")
    private int author_id;

    @JsonProperty("street")
    private String street;
    
    @JsonProperty("number")
    private int number;

    @JsonProperty("zipcode")
    private int zipCode;

}

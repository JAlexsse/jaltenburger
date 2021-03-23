package ayi.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Publishing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publishing_id;
    
    private String publishing_name;

    public Publishing(String name){
        super();
        this.publishing_name = name;
    }

}

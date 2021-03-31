package ayi.bookstore.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ayi.bookstore.model.Adress;
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
    
    @Column
    private String publishing_name;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride( name = "street", column = @Column(name = "adressStreet")),
        @AttributeOverride( name = "number", column = @Column(name = "adressNumber")),
        @AttributeOverride( name = "zipCode", column = @Column(name = "adressZipCode"))
    })
    private Adress adress;

    public Publishing(String name, Adress adress){
        super();
        this.publishing_name = name;
    }

}

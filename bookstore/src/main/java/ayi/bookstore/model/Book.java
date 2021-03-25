package ayi.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String book_name;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToOne(targetEntity = Publishing.class)
    @JoinColumn(name = "publishing_id", referencedColumnName = "publishing_id")
    private Publishing publishing;

    public Book(String book_name, Author author, Publishing publishing){
        super();
        this.book_name = book_name;
        this.author = author;
        this.publishing = publishing;
    }
    

}

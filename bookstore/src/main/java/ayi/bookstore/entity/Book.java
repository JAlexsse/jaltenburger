package ayi.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.math3.util.Precision;

import ayi.bookstore.interfaces.Calculable;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Book implements Calculable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String book_name;

    @Column
    private double price;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToOne(targetEntity = Publishing.class)
    @JoinColumn(name = "publishing_id", referencedColumnName = "publishing_id")
    private Publishing publishing;

    public Book(String book_name, Author author, double price, Publishing publishing){
        super();
        this.book_name = book_name;
        this.author = author;
        this.price = price;
        this.publishing = publishing;
    }

    @Override
    public double calculatePrice() {
        return Precision.round((this.price + (this.price * 0.30)), 2);
    }
    

}

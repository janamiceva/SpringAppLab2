package mk.ukim.finki.emt.lab2.model;

import lombok.Data;
import mk.ukim.finki.emt.lab2.model.enumerations.Category;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Book(String name, Category category, Author author1, Integer availableCopies) {
        this.name=name;
        this.category=category;
        this.author=author1;
        this.availableCopies=availableCopies;
    }

    public Book() {

    }
}

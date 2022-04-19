package mk.ukim.finki.emt.lab2.config;


import mk.ukim.finki.emt.lab2.model.enumerations.Category;
import mk.ukim.finki.emt.lab2.service.AuthorService;
import mk.ukim.finki.emt.lab2.service.BookService;
import mk.ukim.finki.emt.lab2.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DataInitializer {


    private final AuthorService authorService;

    private final CountryService countryService;

    private final BookService bookService;

    public DataInitializer(AuthorService authorService, CountryService countryService, BookService bookService) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.bookService = bookService;
    }


    @PostConstruct
    public void initData() {

        this.countryService.create("United Kingdom", "Europe");
        this.countryService.create("United States", "America");
        this.countryService.create("Greece", "Europe");
        this.countryService.create("Germany", "Europe");
        this.countryService.create("Cyprus", "Europe");
        this.authorService.create("Wiliam", "Shakespeare", 1L);
        this.authorService.create("George", "R. R. Martin", 2L);
        this.authorService.create("Jane", "Austen", 1L);
        this.authorService.create("Homer", "", 3L);
        this.authorService.create("Anne", "Frank", 4L);
        this.authorService.create("Alex", "Michaelides", 5L);

        this.bookService.save("Hamlet", Category.DRAMA, 1L, 15);
        this.bookService.save("A Game of Thrones", Category.NOVEL, 2L, 25);
        this.bookService.save("Pride and Prejudice", Category.NOVEL, 3L, 10);
        this.bookService.save("Iliad & Odyssey", Category.CLASSICS, 4L, 17);
        this.bookService.save("The Diary of a Young Girl", Category.HISTORY, 5L, 23);
        this.bookService.save("The Silent Patient", Category.THRILLER, 6L, 33);
    }
}

package mk.ukim.finki.emt.lab2.service.impl;

import mk.ukim.finki.emt.lab2.model.Author;
import mk.ukim.finki.emt.lab2.model.Book;
import mk.ukim.finki.emt.lab2.model.dto.BookDto;
import mk.ukim.finki.emt.lab2.model.enumerations.Category;
import mk.ukim.finki.emt.lab2.model.exception.AuthorNotFoundException;
import mk.ukim.finki.emt.lab2.model.exception.BookNotFoundException;
import mk.ukim.finki.emt.lab2.repository.AuthorRepository;
import mk.ukim.finki.emt.lab2.repository.BookRepository;
import mk.ukim.finki.emt.lab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long author, Integer availableCopies) {
        Author author1 = this.authorRepository.findById(author).orElseThrow(() -> new AuthorNotFoundException(author));
        Book book = new Book(name, category, author1, availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthor()));
        this.bookRepository.deleteByName(bookDto.getName());
        Book book=new Book(bookDto.getName(), bookDto.getCategory(),author, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies) {
        Book book=this.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        Author author1 = this.authorRepository.findById(author).orElseThrow(() -> new AuthorNotFoundException(author));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author1);
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book=this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        Author author1 = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author1);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book=this.findById(id).orElseThrow(()->new BookNotFoundException(id));
        Integer copies=book.getAvailableCopies();
        book.setAvailableCopies(copies-1);
        return Optional.of(this.bookRepository.save(book));
    }
}

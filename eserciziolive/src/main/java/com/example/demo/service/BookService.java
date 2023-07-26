package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }
    public Optional<Book>getBookById(Long id){
        return bookRepo.findById(id);
    }
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepo.findById(id);
        if (existingBook.isPresent()) {
            Book bookUpdate = existingBook.get();
            bookUpdate.setTitle(updatedBook.getTitle());
            bookUpdate.setAuthor(updatedBook.getAuthor());
            bookUpdate.setIsbn(updatedBook.getIsbn());
            return bookRepo.save(bookUpdate);
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }
    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}


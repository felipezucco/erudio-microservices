package com.erudio.bookservice.controller;

import com.erudio.bookservice.model.Book;
import com.erudio.bookservice.proxy.CambioProxy;
import com.erudio.bookservice.repository.BookRepository;
import com.erudio.bookservice.response.CambioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        String port = environment.getProperty("local.server.port");
        Optional<Book> optBook = repository.findById(id);
        if (optBook.isPresent()) {
            Book book = optBook.get();
            book.setEnvironment(port);

            CambioResponse cambio = proxy.getCambio(book.getPrice(), "USD", currency);

            assert cambio != null;
            book.setPrice(cambio.getConvertedValue());
            return book;
        }



        throw new RuntimeException("Book no exist.");
    }

}

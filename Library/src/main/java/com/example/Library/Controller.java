package com.example.Library;

import com.example.Library.DTOs.BookDTO;
import com.example.Library.DTOs.ReaderDTO;
import com.example.Library.Entities.Book;
import com.example.Library.Entities.Reader;
import com.example.Library.Exceptions.LentBookException;
import com.example.Library.Exceptions.MultipleEntitiesException;
import com.example.Library.Exceptions.NoEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {
    @Autowired
    Service service;

    private final ResponseEntity<Integer> BAD_REQUEST = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
    private final ResponseEntity<Integer> CREATED = new ResponseEntity<Integer>(HttpStatus.CREATED);
    private final ResponseEntity<Integer> OK = new ResponseEntity<Integer>(HttpStatus.OK);


    @PostMapping("/add_book")
    public ResponseEntity<Integer> add(@RequestParam String ISBN,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) Integer numberOfPages) {
        try {
            service.add(new Book(ISBN, title, firstName, lastName, numberOfPages, null));
        } catch (MultipleEntitiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return CREATED;
    }

    @PostMapping("/add_reader")
    public ResponseEntity<Integer> add(@RequestParam String PESEL,
                                       @RequestParam(required = false) String firstName,
                                       @RequestParam(required = false) String lastName) {
        try {
            service.add(new Reader(PESEL, firstName, lastName));
        } catch (MultipleEntitiesException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return CREATED;
    }

    @PutMapping("/borrow_book")
    public ResponseEntity<Integer> borrow(@RequestParam String ISBN,
                                          @RequestParam String PESEL) {
        try {
            service.borrow(ISBN, PESEL);
        } catch (NoEntityException | LentBookException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return OK;
    }

    @PutMapping("/give_back_book")
    public ResponseEntity<Integer> giveBack(@RequestParam String ISBN) {
        try {
            service.giveBack(ISBN);
        } catch (NoEntityException | LentBookException e) {
            System.out.println(e.getMessage());
            return BAD_REQUEST;
        }
        return OK;
    }

    @GetMapping("/book")
    public ResponseEntity<BookDTO> getBook(@RequestParam String ISBN) {
        try {
            return new ResponseEntity<BookDTO>(service.getBook(ISBN), HttpStatus.OK);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            Book empty = new Book(null, null, null, null, 0, 0);
            return new ResponseEntity<BookDTO>(new BookDTO(empty), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reader")
    public ResponseEntity<ReaderDTO> getReader(@RequestParam String PESEL) {
        try {
            return new ResponseEntity<ReaderDTO>(service.getReader(PESEL), HttpStatus.OK);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            Reader empty = new Reader(null, null, null);
            return new ResponseEntity<ReaderDTO>(new ReaderDTO(empty, new ArrayList<String>()), HttpStatus.BAD_REQUEST);
        }
    }
}

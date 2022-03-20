package com.example.Library.DTOs;

import com.example.Library.Entities.Book;

public class BookDTO {
    private String ISBN;
    private String title;
    private String  firstName, lastName;
    private Integer numberOfPages;

    public BookDTO(Book b) {
        this.ISBN = b.getISBN();
        this.title = b.getTitle();
        this.firstName = b.getFirstName();
        this.lastName = b.getLastName();
        this.numberOfPages = b.getNumberOfPages();
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}

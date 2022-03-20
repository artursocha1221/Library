package com.example.Library.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ISBN;
    private String title;
    private String firstName, lastName;
    private Integer numberOfPages;
    private Integer whoHas;

    public Book() {
    }

    public Book(String ISBN, String title, String firstName, String lastName, Integer numberOfPages, Integer whoHas) {
        this.ISBN = ISBN;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPages = numberOfPages;
        this.whoHas = whoHas;
    }

    public Long getId() {
        return id;
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

    public Integer getWhoHas() {
        return whoHas;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setWhoHas(Integer whoHas) {
        this.whoHas = whoHas;
    }
}

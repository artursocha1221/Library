package com.example.Library.DTOs;

import com.example.Library.Entities.Reader;

import java.util.ArrayList;

public class ReaderDTO {
    private String PESEL;
    private String firstName, lastName;
    private ArrayList<String> booksISBN;

    public ReaderDTO(Reader reader, ArrayList<String> booksISBN) {
        this.PESEL = reader.getPESEL();
        this.firstName = reader.getFirstName();
        this.lastName = reader.getLastName();
        this.booksISBN = booksISBN;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getBooksISBN() {
        return booksISBN;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooksISBN(ArrayList<String> booksISBN) {
        this.booksISBN = booksISBN;
    }
}

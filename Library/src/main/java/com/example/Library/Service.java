package com.example.Library;

import com.example.Library.DTOs.BookDTO;
import com.example.Library.DTOs.ReaderDTO;
import com.example.Library.Entities.Book;
import com.example.Library.Entities.Reader;
import com.example.Library.Exceptions.LentBookException;
import com.example.Library.Exceptions.MultipleEntitiesException;
import com.example.Library.Exceptions.NoEntityException;
import com.example.Library.Repos.BookRepo;
import com.example.Library.Repos.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

@Component
public class Service {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ReaderRepo readerRepo;

    private boolean isCorrectISBN(String ISBN) {
        if (ISBN.length() != 17)
            return false;
        int sum = 0, realIndex = -1;
        for (int i = 0; i < ISBN.length() - 1; ++i) {
            char c = ISBN.charAt(i);
            if (!((c >= '0' && c <= '9') || c == '-'))
                return false;
            if (c != '-') {
                ++realIndex;
                sum += ((realIndex & 1) == 0) ? c - '0' : 3 * (c - '0');
            }
        }
        char shouldBe = (sum % 10 == 0) ? '0' : (char) (10 - (sum % 10) + '0');
        return ISBN.charAt(16) == shouldBe;
    }

    public void add(Book book) throws MultipleEntitiesException, IllegalArgumentException {
        if (bookRepo.findByISBN(book.getISBN()).size() != 0)
            throw new MultipleEntitiesException("Multiple books");
        if (!isCorrectISBN(book.getISBN()))
            throw new IllegalArgumentException("Illegal ISBN");
        bookRepo.save(book);
    }

    private boolean isCorrectPESEL(String PESEL) {
        if (PESEL.length() != 11)
            return false;
        int[] costs = new int[] {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;
        for (int i = 0; i < PESEL.length(); ++i) {
            char c = PESEL.charAt(i);
            if (!(c >= '0' && c <= '9'))
                return false;
            sum += (c - '0') * costs[i];
        }
        return sum % 10 == 0;
    }

    public void add(Reader reader) throws MultipleEntitiesException, IllegalArgumentException {
        if (readerRepo.findByPESEL(reader.getPESEL()).size() != 0)
            throw new MultipleEntitiesException("Multiple readers");
        if (!isCorrectPESEL(reader.getPESEL()))
            throw new IllegalArgumentException("Illegal PESEL");
        readerRepo.save(reader);
    }

    public void borrow(String ISBN, String PESEL) throws NoEntityException, LentBookException {
        ArrayList<Reader> readers = readerRepo.findByPESEL(PESEL);
        ArrayList<Book> books = bookRepo.findByISBN(ISBN);
        if (readers.size() * books.size() == 0)
            throw new NoEntityException("Required entity doesn't exist");
        if (books.get(0).getWhoHas() != null)
            throw new LentBookException("Required book is lent");
        bookRepo.updateWhoHas(ISBN, readers.get(0).getId());
    }

    public void giveBack(String ISBN) throws NoEntityException, LentBookException {
        ArrayList<Book> books = bookRepo.findByISBN(ISBN);
        if (books.size() == 0)
            throw new NoEntityException("Required entity doesn't exist");
        if (books.get(0).getWhoHas() == null)
            throw new LentBookException("Required book isn't lent");
        bookRepo.updateWhoHas(ISBN, null);
    }

    public BookDTO getBook(String ISBN) throws NoEntityException {
        ArrayList<Book> books = bookRepo.findByISBN(ISBN);
        if (books.size() == 0)
            throw new NoEntityException("Required entity doesn't exist");
        return new BookDTO(books.get(0));
    }

    public ReaderDTO getReader(String PESEL) throws NoEntityException {
        ArrayList<Reader> readers = readerRepo.findByPESEL(PESEL);
        if (readers.size() == 0)
            throw new NoEntityException("Required entity doesn't exist");
        ArrayList<String> booksISBN = bookRepo.findByWhoHas(readers.get(0).getId().intValue());
        return new ReaderDTO(readers.get(0), booksISBN);
    }
}

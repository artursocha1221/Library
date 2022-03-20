package com.example.Library.Repos;

import com.example.Library.Entities.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface BookRepo  extends CrudRepository<Book, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.whoHas = ?2 WHERE b.ISBN = ?1")
    //second parameter should be Integer
    void updateWhoHas(String ISBN, Long id);

    @Query("SELECT b FROM Book b WHERE b.ISBN = ?1")
    ArrayList<Book> findByISBN(String ISBN);

    @Query("SELECT b.ISBN FROM Book b WHERE b.whoHas = ?1")
    ArrayList<String> findByWhoHas(Integer id);
}

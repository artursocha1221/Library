package com.example.Library.Repos;

import com.example.Library.Entities.Reader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ReaderRepo extends CrudRepository<Reader, Long> {
    @Query("SELECT r FROM Reader r WHERE r.PESEL = ?1")
    ArrayList<Reader> findByPESEL(String PESEL);
}

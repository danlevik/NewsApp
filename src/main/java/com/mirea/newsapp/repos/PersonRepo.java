package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    Person findPersonById(int id);
    Person findPersonByUsername(String username);

    @Modifying
    @Query(value = "delete from person WHERE person.person_id = ?1", nativeQuery = true)
    Integer deleteById(int id);
}

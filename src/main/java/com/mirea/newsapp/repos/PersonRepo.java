package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    Person findPersonById(int id);
    Person findPersonByUsername(String username);

    Long deleteById(int id);
}

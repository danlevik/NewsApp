package com.mirea.newsapp.services;


import com.mirea.newsapp.models.Person;
import com.mirea.newsapp.repos.PersonRepo;
import com.mirea.newsapp.repos.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private PersonRepo personRepo;

    @Autowired
    private RoleService roleService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person getPersonById(int id){
        return personRepo.findPersonById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return personRepo.findPersonByUsername(s);
    }

    public int getUserId(Authentication authentication){
        if (authentication == null)
            return 0;
        else
            return ((Person)loadUserByUsername(authentication.getName())).getId();
    }

    public String getUserRole(Authentication authentication) {
        if (authentication == null)
            return "GUEST";
        else
            return ((Person)loadUserByUsername(authentication.getName())).getRole().getName();
    }

    public List<Person> getAllPersons(){
        return personRepo.findAll();
    }

    public void save(Person person){
        personRepo.save(person);
    }

    public void deletePersonById(int id) {
        personRepo.deleteById(id);
    }

    public void create(String username, String password) {
        Person person = new Person();
        person.setName("Аноним");
        person.setSurname("Анонимный");
        person.setUsername(username);
        person.setPassword(bCryptPasswordEncoder.encode(password));
        person.setRole(roleService.findRoleByName("USER"));
        personRepo.save(person);
    }

    public void update(Person person, String firstname, String surname, String username, String password){
        person.setId(person.getId());
        person.setName(firstname);
        person.setSurname(surname);
        person.setUsername(username);
        person.setPassword(bCryptPasswordEncoder.encode(password));
        personRepo.save(person);
    }
}

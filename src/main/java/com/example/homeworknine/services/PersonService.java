package com.example.homeworknine.services;

import com.example.homeworknine.exceptions.NotFoundException;
import com.example.homeworknine.models.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PersonService extends UserDetailsService {
    Person createPerson(String firstName, String lastName, String email, String username, String password);

    Person getPersonById(Long idPerson) throws NotFoundException;

    Person updatePerson(Person person) throws NotFoundException;

    void deletePerson(Long idPerson) throws NotFoundException;

    List<Person> getAllPersons();
    Person getPersonByUsername(String username);
}

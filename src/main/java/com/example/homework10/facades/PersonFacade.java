package com.example.homework10.facades;

import com.example.homework10.dtos.PersonDto;
import com.example.homework10.exceptions.NotFoundException;

import java.util.List;

public interface PersonFacade {

    PersonDto createPerson(PersonDto personDto);

    PersonDto getPersonById(PersonDto personDto) throws NotFoundException;

    PersonDto updatePerson(PersonDto personDto) throws NotFoundException;

    void deletePerson(PersonDto personDto) throws NotFoundException;

    List<PersonDto> getAllPersons();

    PersonDto getPersonByUsername(PersonDto personDto);
}

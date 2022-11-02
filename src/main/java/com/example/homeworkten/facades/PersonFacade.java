package com.example.homeworkten.facades;

import com.example.homeworkten.dtos.PersonDto;
import com.example.homeworkten.exceptions.NotFoundException;

import java.util.List;

public interface PersonFacade {

    PersonDto createPerson(PersonDto personDto);

    PersonDto getPersonById(PersonDto personDto) throws NotFoundException;

    PersonDto updatePerson(PersonDto personDto) throws NotFoundException;

    void deletePerson(PersonDto personDto) throws NotFoundException;

    List<PersonDto> getAllPersons();

    PersonDto getPersonByUsername(PersonDto personDto);
}

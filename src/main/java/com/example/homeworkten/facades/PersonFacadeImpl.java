package com.example.homeworkten.facades;

import com.example.homeworkten.converters.PersonConverter;
import com.example.homeworkten.dtos.PersonDto;
import com.example.homeworkten.exceptions.NotFoundException;
import com.example.homeworkten.models.Person;
import com.example.homeworkten.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.homeworkten.converters.PersonConverter.convertPersonDtoToPerson;
import static com.example.homeworkten.converters.PersonConverter.convertPersonToPersonDto;

@Component
public class PersonFacadeImpl implements PersonFacade {

    @Autowired
    private PersonService personService;


    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person Person = personService.createPerson(personDto.getFirstName(), personDto.getLastName(),  personDto.getEmail(),  personDto.getUsername(),personDto.getPassword());
        return convertPersonToPersonDto(Person);
    }


    @Override
    public PersonDto getPersonById(PersonDto personDto) throws NotFoundException {
        Person Person=personService.getPersonById(personDto.getIdPerson());
        return convertPersonToPersonDto(Person);
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) throws NotFoundException {
        Person oldPerson=convertPersonDtoToPerson(personDto);
        Person updatedPerson=personService.updatePerson(oldPerson);
        return convertPersonToPersonDto(updatedPerson);
    }

    @Override
    public void deletePerson(PersonDto personDto) throws NotFoundException {
        personService.deletePerson(personDto.getIdPerson());
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersons().stream().map(PersonConverter::convertPersonToPersonDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonByUsername(PersonDto personDto) {
        Person getPerson=personService.getPersonByUsername(personDto.getUsername());
        return convertPersonToPersonDto(getPerson);
    }
}

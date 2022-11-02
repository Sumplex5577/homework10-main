package com.example.homeworkten.controllers;

import com.example.homeworkten.exceptions.NotFoundException;
import com.example.homeworkten.converters.PersonConverter;
import com.example.homeworkten.dtos.PersonDto;
import com.example.homeworkten.facades.PersonFacade;
import com.example.homeworkten.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.example.homeworkten.converters.PersonConverter.convertPersonDtoToPerson;
import static com.example.homeworkten.converters.PersonConverter.convertPersonToPersonDto;
@Slf4j
@Controller
@RequestMapping(path="/person")

public class PersonController {

    private final PersonFacade personFacade;

    public PersonController(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String personIndex(Model model) {
        String message = "Person control page";
        model.addAttribute("message", message);
        return "/person/personIndex";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPersonView(Model model) {
        model.addAttribute("person", new PersonDto());
        return "/person/createPerson";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPerson(@ModelAttribute("person") PersonDto personDto) {
        personFacade.createPerson(personDto);
        log.info("New Person was created with username [{}] and email [{}]", personDto.getUsername(), personDto.getEmail());
        return "/person/createPersonSuccess";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getPersonByIdView(Model model) {
        model.addAttribute("personById", new PersonDto());
        return "/person/getPerson";
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @Transactional
    public String getPersonById(@ModelAttribute("personById") PersonDto personDto, Model model) throws NotFoundException {
        PersonDto personById = (personFacade.getPersonById(personDto));
        model.addAttribute("personById", personById);
        log.info("Person with  following ID [{}] was found", personDto.getIdPerson());
        return "/person/getPersonSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePersonView(Model model) {
        model.addAttribute("person", new PersonDto());
        return "/person/updatePerson";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PUT, RequestMethod.POST})
    @Transactional
    public String updatePerson(@ModelAttribute("person") PersonDto personDto) throws NotFoundException {
        personFacade.updatePerson(personDto);
        log.info("Person with following ID [{}] was updated", personDto.getIdPerson());
        return "/person/updatePersonSuccess";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePersonByIdView(Model model) {
        model.addAttribute("person", new PersonDto());
        return "/person/deletePerson";
    }


    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deletePerson(@ModelAttribute("person") PersonDto personDto) throws NotFoundException {
        personFacade.deletePerson(personDto);
        log.info("Person with following ID [{}] was deleted", personDto.getIdPerson());
        return "/person/deletePersonSuccess";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllPersons(Model model) {
        model.addAttribute("all", personFacade.getAllPersons());
        return "/person/allPersons";
    }
}




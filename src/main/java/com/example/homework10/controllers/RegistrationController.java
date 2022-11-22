package com.example.homework10.controllers;

import com.example.homework10.dtos.PersonDto;
import com.example.homework10.models.Person;
import com.example.homework10.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RegistrationController {

    private final PersonService personService;


    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("personForm", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerPerson(@ModelAttribute("personForm") PersonDto personDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!personDto.getPassword().equals(personDto.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Wrong password");
            return "registration";
        }
        if (personService.createPerson(personDto.getFirstName(), personDto.getLastName(), personDto.getEmail(), personDto.getUsername(), personDto.getPassword()) == null) {
            model.addAttribute("usernameError", "User with this username is already exists");
            return "registration";
        }
        log.info("New user is registered with username [{}] and email [{}]", personDto.getUsername(), personDto.getEmail());
        return "redirect:/mainIndex";
    }

}

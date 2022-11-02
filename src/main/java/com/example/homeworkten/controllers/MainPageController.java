package com.example.homeworkten.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value={"/mainIndex", "/"})
public class MainPageController {
    @RequestMapping(method = RequestMethod.GET)
    public String mainIndex(Model model) {
        String message = "Online shop start page";
        model.addAttribute("message", message);
        return "mainIndex";
    }
}

package com.codewithmosh.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/")
public class HomeController {
    // model, a container for data
    // to pass data from a controller to a view
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Bliss");
        return "index";
    }
}

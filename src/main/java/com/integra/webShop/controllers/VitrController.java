package com.integra.webShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VitrController {
    @GetMapping("/about")
    public String about(Model model)
    {
        return "about";
    }
}

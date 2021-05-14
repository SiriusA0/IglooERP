package com.igloo.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, @RequestParam(required = false) Boolean success) {
        model.addAttribute("success", success);
        return "/home";
    }

}

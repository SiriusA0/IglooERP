package com.igloo.applications.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationsController {


    @GetMapping("/applications")
    public String app(){
        return "applications/applications";
    }

}

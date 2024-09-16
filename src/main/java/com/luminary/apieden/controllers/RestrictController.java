package com.luminary.apieden.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestrictController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/home")
    public String user() {
        return "home";
    }
}

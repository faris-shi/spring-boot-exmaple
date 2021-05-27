package com.github.faris.security.formlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formlogin")
public class FormController {

    @GetMapping("admin/index")
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("management/index")
    public String managementIndex() {
        return "management/index";
    }

    @GetMapping("profile/index")
    public String profileIndex() {
        return "profile/index";
    }
}

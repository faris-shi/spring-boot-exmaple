package com.github.faris.springboot.security.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/admin/index.html")
    public String info() {
        return "info";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/user/profile.html")
    public String profile() {
        return "profile";
    }
}

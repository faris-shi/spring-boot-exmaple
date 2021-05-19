package com.github.faris.springboot.jpa.actuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class HelloController {

    @GetMapping("/info")
    public String info() {
        return "ok";
    }
}

package com.github.faris.security.jwt.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JwtController {

    @GetMapping(value = "my_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> info() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }
}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @RequestMapping("/hello2")
    public String hello(Principal principal){
        return "hello "+principal.getName();
    }

    @RequestMapping("/hello3")
    public String hello2(Principal principal){
        return "hello3 "+principal.getName();
    }
}

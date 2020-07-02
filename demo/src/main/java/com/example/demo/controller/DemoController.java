package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("demoController")
@RequestMapping(value = {"/demo"})
public class DemoController {

    @PostMapping("/getDemo")
    public String getDemo() {
        return "123";
    }

}

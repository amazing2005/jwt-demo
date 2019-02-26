package com.example.tokenjwtdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/user")
    public @ResponseBody  String getUser(){
        return "user";
    }
}

package com.tcbd07.mintproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {
    @RequestMapping("/")
    public String home(){
        return "index";
    }
}

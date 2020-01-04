package com.tcbd07.mintproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController {
    @RequestMapping("/")
    public String home(){

        return "template";
    }

}

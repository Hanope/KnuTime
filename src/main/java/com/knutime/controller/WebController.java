package com.knutime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class WebController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/timetable")
    public String timetable() {
        return "timetable";
    }

    @RequestMapping("/room")
    public String roomView() {
        return "map";
    }
}

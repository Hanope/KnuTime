package com.knutime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimeTableController {

    @RequestMapping("/timetable")
    public String timeTableView() {
        return "timetable";
    }
}

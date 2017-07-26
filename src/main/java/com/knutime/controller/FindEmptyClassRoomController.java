package com.knutime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FindEmptyClassRoomController {

    @RequestMapping("/room")
    public String roomView() {
        return "map";
    }
}

package com.knutime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.Principal;

@Controller
public class WebController {

    @RequestMapping("/")
    public String home(Principal principal) {
        if(principal != null)
            System.out.println(principal.getName());
        return "index";
    }

    @RequestMapping("/room")
    public String roomView() {
        return "map";
    }

    @RequestMapping("/rating")
    public String ratingView() { return "rating"; }

    @ResponseBody
    @RequestMapping("/test/ajax")
    public void testAjax(Principal principal) {
        if(principal != null)
            System.out.println(principal.getName());
    }
}

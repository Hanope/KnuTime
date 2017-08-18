package com.knutime.controller;

import com.knutime.Exception.ResourceNotFoundException;
import com.knutime.Exception.UnauthorizedException;
import com.knutime.domain.timetable.Timetable;
import com.knutime.domain.user.CurrentUser;
import com.knutime.service.timetable.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String timetableMainView() {
        return "/timetable/timetable_main";
    }

    @RequestMapping(value = "/view/{serialNumber}", method = RequestMethod.GET)
    public String timetableView(@PathVariable String serialNumber) {
        if(!timetableService.isExistsTimetable(serialNumber))
            throw new ResourceNotFoundException();

        return "/timetable/timetable_view";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTimeTableView() {
        return "/timetable/timetable_create";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createTimeTable(@ModelAttribute("timetalbe")Timetable timetable, BindingResult result) {
        CurrentUser user = getCurrentUser();

        if(user == null)
            throw new UnauthorizedException();

        Timetable table  = timetableService.createTimetable(user.getId(), timetable);

        return "redirect:/timetable/view/" + table.getSerialNumber();
    }

    private CurrentUser getCurrentUser() {
        return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

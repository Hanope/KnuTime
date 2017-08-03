package com.knutime.controller;

import com.knutime.domain.CurrentUser;
import com.knutime.domain.Timetable;
import com.knutime.service.timetable.TimetableService;
import com.knutime.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    private final TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timeTableService) {
        this.timetableService = timeTableService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String timetableMainView() {
        return "/timetable/timetable_main";
    }

    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.GET)
    public ModelAndView timetableView(@PathVariable String serialNumber) {
        Timetable timetable = timetableService.getTimetable(serialNumber);

        if(timetable == null)
            throw new com.knutime.Exception.ResourceNotFoundException();

        return new ModelAndView("/timetable/timetable_view", "timetable", timetable);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTimeTableView() {
        return "/timetable/timetable_create";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createTimeTable(@ModelAttribute("timetalbe")Timetable timetable, BindingResult result) {
        CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Timetable table  = timetableService.createTimetable(user.getId(), timetable);

        return "redirect:/timetable/" + table.getSerialNumber();
    }
}

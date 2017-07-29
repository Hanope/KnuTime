package com.knutime.controller;

import com.knutime.domain.UserCreateForm;
import com.knutime.domain.validator.UserCreateFormValidator;
import com.knutime.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView userCreateView() {
        return new ModelAndView("join", "form", new UserCreateForm());
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String userCreate(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "join";
        }

        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "이메일이 이미 존재합니다.");
            return "join";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }
}

package com.knutime.domain.validator;

import com.knutime.domain.UserCreateForm;
import com.knutime.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validatePassword(errors, form);
        validateEmail(errors, form);
    }

    private void validatePassword(Errors errors, UserCreateForm form) {
        if(!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "비밀번호가 일치하지 않습니다.");
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
        if(userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "이메일이 이미 존재합니다.");
        }
    }
}

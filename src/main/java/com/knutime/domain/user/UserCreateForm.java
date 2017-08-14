package com.knutime.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateForm {

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    @NotEmpty
    private String nickName = "";

    @NotNull
    private Role role = Role.USER;

    @Override
    public String toString() {
        return "UserCreateForm{" +
                "email=" + email.replaceFirst("@.+", "@***") +
                ", password=***" +
                ", passwordRepeated=***" +
                ", nickName=" + nickName +
                ", role=" + role +
                '}';
    }
}

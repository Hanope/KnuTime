package com.knutime.domain;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

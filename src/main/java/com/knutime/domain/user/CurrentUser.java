package com.knutime.domain.user;

import com.knutime.domain.timetable.Timetable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class CurrentUser extends org.springframework.security.core.userdetails.User{

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getEmail() { return user.getEmail(); }

    public String getNickName() { return user.getNickName(); }

    public Role getRole() {
        return user.getRole();
    }

    public List<Timetable> getTimetables() { return user.getTimetables(); }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }

    public static CurrentUser getCurrentUser() {
        try {
            return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            return null;
        }
    }
}

package com.shop.logic.controller;

import com.shop.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Igor on 04.07.2015.
 */
@ManagedBean(name = "user", eager = true)
@RequestScoped
public class UserController {
    @Autowired
    private UserService userService;

    public Long getCurrentUserId() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByUsername(username).getId();
    }

}

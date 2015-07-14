package com.shop.logic.controller;

import com.shop.logic.exception.UserAlreadyExistsException;
import com.shop.logic.service.UserService;
import com.shop.presentation.form.RegistrationForm;
import com.shop.storage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.Valid;

/**
 * Created by Igor on 27.06.2015.
 */
@ManagedBean(name = "register", eager = true)
@RequestScoped
public class RegistrationController {

    @Autowired
    private User user;

    @Autowired
    private UserService userService;

    public String registerUser() {
        userService.registerUser(user);
        return "login";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

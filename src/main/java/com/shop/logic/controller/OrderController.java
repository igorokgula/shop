package com.shop.logic.controller;

import com.shop.logic.service.OrderService;
import com.shop.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Igor on 04.07.2015.
 */
@ManagedBean(name = "order", eager = true)
@RequestScoped
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

}

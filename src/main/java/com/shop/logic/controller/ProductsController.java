package com.shop.logic.controller;

import com.shop.logic.service.BasketService;
import com.shop.logic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Igor on 04.07.2015.
 */
@ManagedBean(name = "products", eager = true)
@RequestScoped
public class ProductsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public BasketService getBasketService() {
        return basketService;
    }

    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }
}

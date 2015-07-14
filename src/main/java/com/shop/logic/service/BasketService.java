package com.shop.logic.service;

import com.shop.storage.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 29.06.2015.
 */
@Service(value = "basketService")
@Scope("prototype")
public class BasketService {

    private List<Long> productIds;

    public BasketService() {
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public void addOrRemoveProduct(Long productId) {
        if (productIds == null) {
            productIds = new ArrayList<Long>();
        }
        if (productIds.contains(productId)) {
            productIds.remove(productId);
        } else {
            productIds.add(productId);
        }
    }

}

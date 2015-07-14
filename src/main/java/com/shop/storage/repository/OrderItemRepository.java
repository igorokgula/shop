package com.shop.storage.repository;

import com.shop.storage.entity.OrderItem;

/**
 * Created by Igor on 27.06.2015.
 */
public interface OrderItemRepository {
    public Long save(OrderItem orderItem);
}

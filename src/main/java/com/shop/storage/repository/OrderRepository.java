package com.shop.storage.repository;

import com.shop.storage.entity.Order;
import org.omg.CORBA.TIMEOUT;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Igor on 25.06.2015.
 */
public interface OrderRepository {
    public Long save(Order order);
    public Long update(Order order);
    public boolean delete(Order order);

    public List<Order> getAllOrders(Long userId, int startingResult, int resultsCount);
    public List<Order> getDeliveredOrders(Long userId, int startingResult, int resultsCount);
    public List<Order> getCanceledOrders(Long userId, int startingResult, int resultsCount);
    public List<Order> getExpectedDeliveryOrders(Long userId, int startingResult, int resultsCount);
    public List<Order> getByTimeBetweenOrders(Long userId, Timestamp low, Timestamp high, int startingResult, int resultsCount);

    public Long getTotalAllOrders(Long userId);
    public Long getTotalDeliveredOrders(Long userId);
    public Long getTotalCanceledOrders(Long userId);
    public Long getTotalExpectedDeliveryOrders(Long userId);
    public Long getTotalByTimeBetweenOrders(Long userId, Timestamp low, Timestamp high);
}

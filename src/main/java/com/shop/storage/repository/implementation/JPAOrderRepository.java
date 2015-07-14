package com.shop.storage.repository.implementation;

import com.shop.storage.entity.Order;
import com.shop.storage.enums.OrderStatus;
import com.shop.storage.enums.ProductStatus;
import com.shop.storage.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Igor on 27.06.2015.
 */
@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository{
    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    private static final String GET_PRODUCTS_QUERY = "select * from c_order o " +
            "where o.id in (select item.order_id from c_order_item item " +
            "where item.order_status = :status and rownum() = 1 " +
            "group by item.order_id " +
            "order by item.time desc) " +
            "and o.customer_id = :userId)";
    private static final String GET_TOTAL_PRODUCTS_QUERY = "select COUNT(*) from c_order o " +
            "where o.id in (select item.order_id from c_order_item item " +
            "where item.order_status = :status and rownum() = 1 " +
            "group by item.order_id " +
            "order by item.time desc)" +
            " and o.customer_id = :userId";

    public Long save(Order order) {
        entityManager.persist(order);
        return order.getId();
    }


    public Long update(Order order) {
        entityManager.merge(order);
        return order.getId();
    }


    public boolean delete(Order order) {
        entityManager.remove(order);
        return true;
    }


    public List<Order> getAllOrders(Long userId, int startingResult, int resultsCount) {
        TypedQuery<Order> query = entityManager.createNamedQuery("Order.findAll", Order.class);
        query.setParameter("userId", userId);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Order> getDeliveredOrders(Long userId, int startingResult, int resultsCount) {
        Query query = entityManager.createNativeQuery(GET_PRODUCTS_QUERY, Order.class);
        query.setParameter("status", OrderStatus.DELIVERED.getName());
        query.setParameter("userId", userId);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Order> getCanceledOrders(Long userId, int startingResult, int resultsCount) {
        Query query = entityManager.createNativeQuery(GET_PRODUCTS_QUERY, Order.class);
        query.setParameter("status", OrderStatus.CANCELED.getName());
        query.setParameter("userId", userId);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Order> getExpectedDeliveryOrders(Long userId, int startingResult, int resultsCount) {
        Query query = entityManager.createNativeQuery(GET_PRODUCTS_QUERY, Order.class);
        query.setParameter("status", OrderStatus.EXPECTED_DELIVERY.getName());
        query.setParameter("userId", userId);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Order> getByTimeBetweenOrders(Long userId, Timestamp low, Timestamp high, int startingResult, int resultsCount) {
        TypedQuery<Order> query = entityManager.createNamedQuery("Order.findByTimeBetween", Order.class);
        query.setParameter("userId", userId);
        query.setParameter("lowTime", low);
        query.setParameter("highTime", high);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public Long getTotalAllOrders(Long userId) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Order.findTotalAll", Long.class);
        query.setParameter("userId", userId);
        return query.getResultList().get(0);
    }


    public Long getTotalDeliveredOrders(Long userId) {
        Query query = entityManager.createNativeQuery(GET_TOTAL_PRODUCTS_QUERY);
        query.setParameter("status", OrderStatus.DELIVERED.getName());
        query.setParameter("userId", userId);
        return (Long)query.getResultList().get(0);
    }

    @Override
    public Long getTotalCanceledOrders(Long userId) {
        Query query = entityManager.createNativeQuery(GET_TOTAL_PRODUCTS_QUERY);
        query.setParameter("status", OrderStatus.CANCELED.getName());
        query.setParameter("userId", userId);
        return (Long)query.getResultList().get(0);
    }

    @Override
    public Long getTotalExpectedDeliveryOrders(Long userId) {
        Query query = entityManager.createNativeQuery(GET_TOTAL_PRODUCTS_QUERY);
        query.setParameter("status", OrderStatus.EXPECTED_DELIVERY.getName());
        query.setParameter("userId", userId);
        return (Long)query.getResultList().get(0);
    }

    public Long getTotalByTimeBetweenOrders(Long userId, Timestamp low, Timestamp high) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Order.findTotalByTimeBetween", Long.class);
        query.setParameter("userId", userId);
        query.setParameter("lowTime", low);
        query.setParameter("highTime", high);
        return query.getResultList().get(0);
    }
}

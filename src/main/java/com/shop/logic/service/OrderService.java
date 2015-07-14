package com.shop.logic.service;

import com.shop.storage.entity.Order;
import com.shop.storage.entity.OrderItem;
import com.shop.storage.entity.Product;
import com.shop.storage.enums.OrderStatus;
import com.shop.storage.enums.ProductStatus;
import com.shop.storage.repository.OrderItemRepository;
import com.shop.storage.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 27.06.2015.
 */
@Service(value = "orderService")
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BasketService basketService;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Long makeOrder(Long userId) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        Order order = new Order();
        order.setUser(userService.getUserById(userId));
        order.setTime(currentTime);
        List<Product> products = getProducts(basketService.getProductIds());
        order.setProducts(products);
        order.setPrice(countPrice(products));
        Long orderId = orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setCreateTime(currentTime);
        orderItem.setOrderStatus(OrderStatus.EXPECTED_DELIVERY);
        orderItemRepository.save(orderItem);

        buyProducts(basketService.getProductIds());

        return orderId;
    }

    private List<Product> getProducts(List<Long> productIds) {
        List<Product> rez = new ArrayList<Product>();
        for (Long id : productIds) {
            rez.add(productService.getById(id));
        }
        return rez;
    }

    private BigDecimal countPrice(List<Product> productList) {
        BigDecimal sum = new BigDecimal(0);
        for (Product p : productList) {
            sum = sum.add(p.getPrice());
        }
        return sum;
    }

    private void buyProducts(List<Long> productList) {
        for (Long id : productList) {
            productService.setStatus(id, ProductStatus.BOUGHT);
        }
    }

    public Long update(Long orderId, OrderStatus orderStatus) {
        //todo
        return null;
    }

    public boolean delete(Order order) {
        return orderRepository.delete(order);
    }

    public List<Order> getAllOrders(Long userId, int startingResult, int resultsCount) {
        return orderRepository.getAllOrders(userId, startingResult, resultsCount);
    }

    public List<Order> getDeliveredOrders(Long userId, int startingResult, int resultsCount) {
        return orderRepository.getDeliveredOrders(userId, startingResult, resultsCount);
    }

    public List<Order> getCanceledOrders(Long userId, int startingResult, int resultsCount) {
        return orderRepository.getCanceledOrders(userId, startingResult, resultsCount);
    }

    public List<Order> getExpectedDeliveryOrders(Long userId, int startingResult, int resultsCount) {
        return orderRepository.getExpectedDeliveryOrders(userId, startingResult, resultsCount);
    }

    public List<Order> getByTimeBetweenOrders(Long userId, Timestamp low, Timestamp high, int startingResult, int resultsCount) {
        return orderRepository.getByTimeBetweenOrders(userId, low, high, startingResult, resultsCount);
    }

    public Long getTotalAllOrders(Long userId) {
        return orderRepository.getTotalAllOrders(userId);
    }

    public Long getTotalDeliveredOrders(Long userId) {
        return orderRepository.getTotalDeliveredOrders(userId);
    }

    public Long getTotalCanceledOrders(Long userId) {
        return orderRepository.getTotalCanceledOrders(userId);
    }

    public Long getTotalExpectedDeliveryOrders(Long userId) {
        return orderRepository.getTotalExpectedDeliveryOrders(userId);
    }

    public Long getTotalByTimeBetweenOrders(Long userId, Timestamp low, Timestamp high) {
        return orderRepository.getTotalByTimeBetweenOrders(userId, low, high);
    }
}

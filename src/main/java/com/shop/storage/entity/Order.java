package com.shop.storage.entity;

import com.shop.storage.enums.OrderStatus;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Igor on 25.06.2015.
 */
@Entity
@Table(name = "c_order")
@NamedQueries({
        @NamedQuery(name = "Order.findAll",
                query = "SELECT o FROM Order o where o.user = :userId"),
        @NamedQuery(name = "Order.findByTimeBetween",
                query = "SELECT o FROM Order o where (o.time between :lowTime and  :highTime) and o.user = :userId"),
        @NamedQuery(name = "Order.findTotalAll",
                query = "SELECT COUNT(o) FROM Order o where o.user = :userId"),
        @NamedQuery(name = "Order.findTotalByTimeBetween",
                query = "SELECT COUNT(o) FROM Order o where (o.time between :lowTime and  :highTime) and o.user = :userId")
})

public class Order {

    private Long id;
    private Timestamp time;
    private BigDecimal price;
    private User user;

    public Order() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

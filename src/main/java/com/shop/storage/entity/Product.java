package com.shop.storage.entity;

import com.shop.storage.enums.ProductStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Igor on 25.06.2015.
 */
@Entity
@Table(name = "c_product")
@NamedQueries(value = {
        @NamedQuery(name = "Product.findAll",
                query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findByPartOfName",
                query = "SELECT p FROM Product p WHERE p.name LIKE :code"),
        @NamedQuery(name = "Product.findByPartOfBrandName",
                query = "SELECT p FROM Product p WHERE p.brandName LIKE :code"),
        @NamedQuery(name = "Product.findByPriceBetween",
                query = "SELECT p FROM Product p WHERE p.price BETWEEN :lowPrice and :highPrice"),
        @NamedQuery(name = "Product.findFiltered",
                query = "SELECT p FROM Product p WHERE p.name LIKE :codeName AND p.brandName LIKE :codeBrand AND p.price BETWEEN :lowPrice and :highPrice"),
        @NamedQuery(name = "Product.findTotalAll",
                query = "SELECT COUNT(p) FROM Product p"),
        @NamedQuery(name = "Product.findTotalByPartOfName",
                query = "SELECT COUNT(p) FROM Product p WHERE p.name LIKE :code"),
        @NamedQuery(name = "Product.findTotalByPartOfBrandName",
                query = "SELECT COUNT(p) FROM Product p WHERE p.brandName LIKE :code"),
        @NamedQuery(name = "Product.findTotalByPriceBetween",
                query = "SELECT COUNT(p) FROM Product p WHERE p.price BETWEEN :lowPrice and :highPrice"),
        @NamedQuery(name = "Product.findTotalFiltered",
                query = "SELECT COUNT(p) FROM Product p WHERE p.name LIKE :codeName AND p.brandName LIKE :codeBrand AND p.price BETWEEN :lowPrice and :highPrice")
})
public class Product {

    private Long id;
    private String name;
    private String brandName;
    private String description;
    private BigDecimal price;
    private ProductStatus productStatus;
    private Order order;

    public Product() {
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "brandname")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

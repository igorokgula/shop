package com.shop.storage.repository.implementation;

import com.shop.storage.entity.OrderItem;
import com.shop.storage.entity.Product;
import com.shop.storage.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import java.math.BigInteger;

/**
 * Created by Igor on 27.06.2015.
 */
@Repository("orderItemRepository")
public class JPAOrderItemRepository implements OrderItemRepository {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    @Autowired
    private JPAProductRepository jpaProductRepository;

    @Override
    public Long save(OrderItem orderItem) {
        entityManager.persist(orderItem);
        return orderItem.getId();
    }

    public Long run() {

        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM c_product");

        BigInteger l = (BigInteger)query.getSingleResult();

        System.out.println("==================");
        for (Product product : jpaProductRepository.getAllProducts(0, 10)) {
            System.out.println(product.getBrandName());
        }
        return 1l;
    }
}

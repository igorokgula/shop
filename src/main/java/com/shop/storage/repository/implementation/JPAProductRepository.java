package com.shop.storage.repository.implementation;

import com.shop.storage.entity.Product;
import com.shop.storage.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 27.06.2015.
 */
@Repository("productRepository")
public class JPAProductRepository implements ProductRepository {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    @Override
    public Long save(Product product) {
        entityManager.persist(product);
        return product.getId();
    }

    @Override
    public Long update(Product product) {
        entityManager.merge(product);
        return product.getId();
    }

    @Override
    public boolean delete(Product product) {
        entityManager.remove(product);
        return true;
    }

    @Override
    public Product getById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> getByIds(List<Long> ids) {
        List<Product> rez = new ArrayList<Product>();
        for (Long l : ids) {
            rez.add(getById(l));
        }
        return rez;
    }

    @Override
    public List<Product> getAllProducts(int startingResult, int resultsCount) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findAll", Product.class);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Product> getProductsByName(String name, int startingResult, int resultsCount) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findByPartOfName", Product.class);
        query.setParameter("code", "%" + name + "%");
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Product> getProductsByBrandName(String brandName, int startingResult, int resultsCount) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findByPartOfBrandName", Product.class);
        query.setParameter("code", "%" + brandName + "%");
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Product> getProductsPriceBetween(BigDecimal low, BigDecimal high, int startingResult, int resultsCount) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findByPriceBetween", Product.class);
        query.setParameter("lowPrice", low);
        query.setParameter("highPrice", high);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<Product> getFilteredProducts(String name, String brandName, BigDecimal low, BigDecimal high, int startingResult, int resultsCount) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findFiltered", Product.class);
        query.setParameter("codeName", "%" + name + "%");
        query.setParameter("codeBrand", "%" + brandName + "%");
        query.setParameter("lowPrice", low);
        query.setParameter("highPrice", high);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public Long getTotalAllProducts() {
        TypedQuery<Long> query = entityManager.createNamedQuery("Product.findTotalAll", Long.class);
        return query.getResultList().get(0);
    }

    @Override
    public Long getTotalProductsByName(String name) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Product.findTotalByPartOfName", Long.class);
        query.setParameter("code", "%" + name + "%");
        return query.getResultList().get(0);
    }

    @Override
    public Long getTotalProductsByBrandName(String brandName) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Product.findTotalByPartOfBrandName", Long.class);
        query.setParameter("code", "%" + brandName + "%");
        return query.getResultList().get(0);
    }

    @Override
    public Long getTotalProductsPriceBetween(BigDecimal low, BigDecimal high) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Product.findTotalByPriceBetween", Long.class);
        query.setParameter("lowPrice", low);
        query.setParameter("highPrice", high);
        return query.getResultList().get(0);
    }

    @Override
    public Long getTotalFilteredProducts(String name, String brandName, BigDecimal low, BigDecimal high) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Product.findTotalFiltered", Long.class);
        query.setParameter("codeName", "%" + name + "%");
        query.setParameter("codeBrand", "%" + brandName + "%");
        query.setParameter("lowPrice", low);
        query.setParameter("highPrice", high);
        return query.getResultList().get(0);
    }
}
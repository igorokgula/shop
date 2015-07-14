package com.shop.storage.repository;


import com.shop.storage.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Igor on 25.06.2015.
 */
public interface ProductRepository {
    public Long save(Product product);
    public Long update(Product product);
    public boolean delete(Product product);
    public Product getById(Long id);
    public List<Product> getByIds(List<Long> ids);
    public List<Product> getAllProducts(int startingResult, int resultsCount);
    public List<Product> getProductsByName(String name,int startingResult, int resultsCount);
    public List<Product> getProductsByBrandName(String brandName, int startingResult, int resultsCount);
    public List<Product> getProductsPriceBetween(BigDecimal low, BigDecimal high, int startingResult, int resultsCount);
    public List<Product> getFilteredProducts(String name, String brandName, BigDecimal low, BigDecimal high, int startingResult, int resultsCount);
    public Long getTotalAllProducts();
    public Long getTotalProductsByName(String name);
    public Long getTotalProductsByBrandName(String brandName);
    public Long getTotalProductsPriceBetween(BigDecimal low, BigDecimal high);
    public Long getTotalFilteredProducts(String name, String brandName, BigDecimal low, BigDecimal high);
}

package com.shop.storage.repository.implementation;

import com.shop.storage.repository.UserRepository;
import com.shop.storage.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Created by Igor on 25.06.2015.
 */
@Repository("userRepository")
public class JPAUserRepository implements UserRepository {

    @PersistenceContext(unitName = "myUnit")
    private EntityManager entityManager;

    public JPAUserRepository() {
    }

    @Override
    public Long save(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public Long update(User user) {
        entityManager.merge(user);
        return user.getId();
    }

    @Override
    public boolean delete(User user) {
        entityManager.remove(user);
        return true;
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("name", username);
        return query.getSingleResult();
    }

    @Override
    public List<User> getAllUsers(int startingResult, int resultsCount) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByPartOfName(String name, int startingResult, int resultsCount) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findByPartOfName", User.class);
        query.setParameter("code", "%" + name + "%");
        query.setFirstResult(startingResult);
        query.setMaxResults(resultsCount);
        return query.getResultList();
    }

    @Override
    public Long getTotalAllUsers() {
        TypedQuery<Long> query = entityManager.createNamedQuery("User.findAll", Long.class);
        return query.getResultList().get(0);
    }

    @Override
    public Long getTotalUsersByName(String name) {
        TypedQuery<Long> query = entityManager.createNamedQuery("User.findByPartOfName", Long.class);
        query.setParameter("code", "%" + name + "%");
        return query.getResultList().get(0);
    }
}
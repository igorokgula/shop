package com.shop.storage.repository;

import com.shop.storage.entity.User;

import java.util.List;

/**
 * Created by Igor on 25.06.2015.
 */
public interface UserRepository {
    public Long save(User user);
    public Long update(User user);
    public boolean delete(User user);
    public User getUserById(Long id);
    public User getUserByUsername(String username);
    public List<User> getAllUsers(int startingResult, int resultsCount);
    public List<User> getUsersByPartOfName(String name, int startingResult, int resultsCount);
    public Long getTotalAllUsers();
    public Long getTotalUsersByName(String name);
}

package com.shop.logic.service;

import com.shop.logic.exception.UserAlreadyExistsException;
import com.shop.presentation.form.RegistrationForm;
import com.shop.storage.entity.User;
import com.shop.storage.enums.Role;
import com.shop.storage.repository.UserRepository;
import org.hibernate.internal.util.xml.DTDEntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 26.06.2015.
 */
@Service(value = "userService")
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean exists(String username) {
        return (getUserByUsername(username) != null);
    }

    public List<User> getUsersPageByPartOfName(String partOfName, int page, int resultsPerPage) {
        return userRepository.getUsersByPartOfName(partOfName, (page - 1) * resultsPerPage, resultsPerPage);
    }

    public boolean delete(User user) {
        return userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }

    public List<User> getAllUsers(int startingResult, int resultsCount) {
        return userRepository.getAllUsers(startingResult, resultsCount);
    }

    public Long getTotalAllUsers() {
        return userRepository.getTotalAllUsers();
    }

    public Long getTotalUsersByName(String name) {
        return userRepository.getTotalUsersByName(name);
    }

    @Transactional()
    public void registerUser(User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/shop", "igor", "1");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from c_user");
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
            }
            System.out.println("success!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

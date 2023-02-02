package com.example.test_api.service;

import com.example.test_api.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    public User getUser(User user);

    User getUser(String username);

    public User insertIntoDatabase(User user);
}

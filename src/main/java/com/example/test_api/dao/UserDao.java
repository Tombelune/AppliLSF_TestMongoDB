package com.example.test_api.dao;

import com.example.test_api.entity.User;
import org.springframework.data.repository.CrudRepository;
public interface UserDao extends CrudRepository<User, String> {

}

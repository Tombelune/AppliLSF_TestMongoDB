package com.example.test_api.service;

import com.example.test_api.dao.UserDao;
import com.example.test_api.entity.User;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.persistence.EntityManager;
import java.util.Arrays;

public class UserServiceImp implements UserService {
    private final EntityManager entityManager;
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;
    @Autowired
    public UserServiceImp(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(User user) {
        MongoDatabase database = client.getDatabase("AppliLSF");
        MongoCollection<Document> usersCollection = database.getCollection("users");
        AggregateIterable<Document> resultUsername = usersCollection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", user.getUsername())
                                .append("path", "username")))));
        AggregateIterable<Document> resultPassword = usersCollection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", user.getPassword())
                                .append("path", "password")))));
        resultUsername.forEach(doc -> {
            if (doc.containsValue(user.getUsername())) {
                resultPassword.forEach(doc2 -> {
                    if (doc2.containsValue(user.getPassword())) {
                        user.setPassword(user.getPassword());
                    }
                });
                user.setUsername(user.getUsername());
            }
        });
        return user;
    }

    @Override
    public User insertIntoDatabase(User user) {
        return userDao.save(user);
    }
}

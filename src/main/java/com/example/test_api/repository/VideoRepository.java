package com.example.test_api.repository;

import com.example.test_api.model.VideoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<VideoModel, String> {
}

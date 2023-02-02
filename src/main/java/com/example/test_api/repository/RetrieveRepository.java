package com.example.test_api.repository;

import com.example.test_api.model.VideoModel;
import com.mongodb.client.gridfs.GridFSBucket;

import java.util.List;

public interface RetrieveRepository {
    List<VideoModel> retrieveAllFilesFromBucket(GridFSBucket bucket);
    List<VideoModel> retrieveWord(String word);
}

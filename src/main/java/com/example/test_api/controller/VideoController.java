package com.example.test_api.controller;

import com.example.test_api.model.VideoModel;
import com.example.test_api.repository.RetrieveRepository;
import com.example.test_api.repository.VideoRepository;
import com.mongodb.client.gridfs.GridFSBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoRepository repo;

    @Autowired
    RetrieveRepository retrieved;

    @GetMapping("/allVideos")
    public List<VideoModel> getAllVideos() {
        return repo.findAll();
    }

    public VideoModel createFile(VideoModel video) {
        return repo.save(video);
    }
    @GetMapping("/videos/{text}")
    public List<VideoModel> listBuckets(GridFSBucket bucket){
        return repo.findAll();
    }

    public List<VideoModel> retrieveAllFilesFromBucket(GridFSBucket bucket) {
        return retrieved.retrieveAllFilesFromBucket(bucket);
    }

    public List<VideoModel> retrieveWord(String word) {
        return retrieved.retrieveWord(word);
    }

}

package com.example.test_api.repository;

import com.example.test_api.model.VideoModel;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RetrieveRepositoryImpl implements RetrieveRepository {

    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;
    @Override
    public List<VideoModel> retrieveAllFilesFromBucket(GridFSBucket bucket) {
        List<VideoModel> listFiles = new ArrayList<>();
        MongoDatabase database = client.getDatabase("AppliLSF");
        MongoCollection<Document> videoFilesCollection = database.getCollection("Videos.files");
        AggregateIterable<Document> result = videoFilesCollection.aggregate(Arrays.asList(new Document("$search",
                new Document("exists",
                        new Document("path", "filename")))));

        result.forEach(doc -> listFiles.add(converter.read(VideoModel.class, doc)));
        return listFiles;
    }

    public List<VideoModel> retrieveWord(String word) {

        List<VideoModel> listMetadata = new ArrayList<>();
        MongoDatabase database = client.getDatabase("AppliLSF");
        MongoCollection<Document> videoFilesCollection = database.getCollection("Videos.files");

        AggregateIterable<Document> result = videoFilesCollection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", word)
                                .append("path", "metadata.word")))));

        result.forEach(doc -> {
            listMetadata.add(converter.read(VideoModel.class, doc));

        });
        return listMetadata;
    }
}

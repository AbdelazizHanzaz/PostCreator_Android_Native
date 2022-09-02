package com.example.postcreator.repository;

import android.app.Application;


import androidx.lifecycle.LiveData;

import com.example.postcreator.database.DbHelper;
import com.example.postcreator.pojo.Post;

import java.util.List;

public class PostsRepository {

    //GetData from Database(Sql or Room) or Retrofit with web service

    private final DbHelper db;

    public PostsRepository(Application application){
            db = DbHelper.getMyInstance(application);

    }

    public LiveData<List<Post>> getPosts(){
        return db.getPosts();
    }
    public LiveData<Long> addPost(Post post){
        return db.addPost(post);
    }
}

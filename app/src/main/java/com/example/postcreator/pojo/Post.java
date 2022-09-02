package com.example.postcreator.pojo;

public class Post {

    private int id;
    private int userId;
    private int mediaId;

    private String title;
    private String description;

    public Post(int id, int userId, int mediaId, String title, String description) {
        this.id = id;
        this.userId = userId;
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;

    }


    public Post(int userId, int mediaId, String title, String description) {
        this.userId = userId;
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;

    }




    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

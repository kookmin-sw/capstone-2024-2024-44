package com.example.uploadpost.models;

public class Post {
    public String title;
    public String content;

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String title, String body) {
        this.title = title;
        this.content = body;
    }
}

package com.webboard.repository;

import com.webboard.model.Post;

import java.util.List;

public interface PostRepository {
    public List<Post> getPostList();
}

package com.webboard.repository;

import com.webboard.model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getPostList();

    void update(Post post);

    Post insert(Post post);
}

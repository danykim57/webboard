package com.webboard.repository;

import com.webboard.model.Post;
import com.webboard.model.commons.Id;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> getPostList();

    void update(Post post);

    Post insert(Post post);

    Optional<Post> findByid(Id<Post, Long> postId);
}

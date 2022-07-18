package com.webboard.service;

import com.webboard.model.Post;
import com.webboard.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.getPostList();
    }

    @Transactional
    public Post write(Post post) {
        return insert(post);
    }

    @Transactional
    public Post modify(Post post) {
        update(post);
        return post;
    }

    private Post insert(Post post) {
        return postRepository.insert(post);
    }

    private void update(Post post) {
        postRepository.update(post);
    }
}

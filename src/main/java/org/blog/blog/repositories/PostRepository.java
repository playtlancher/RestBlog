package org.blog.blog.repositories;

import org.blog.blog.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Integer> {
    public List<Post> findAll();
}

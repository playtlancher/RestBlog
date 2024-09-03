package org.blog.blog.repositories;

import org.blog.blog.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByPostId(int postId);
}

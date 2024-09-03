package org.blog.blog.servises;


import org.blog.blog.dto.CommentDTO;
import org.blog.blog.dto.PostDTO;
import org.blog.blog.entities.Comment;
import org.blog.blog.entities.Post;

import java.util.List;


public interface PostService {
    PostDTO addPost(PostDTO postDTO);
    List<PostDTO> getPosts();

    Iterable<CommentDTO> getComments(int postId);
    CommentDTO addComment(CommentDTO commentDTO);

    PostDTO getById(int id);
}

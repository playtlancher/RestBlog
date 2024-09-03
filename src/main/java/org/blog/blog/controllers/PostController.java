package org.blog.blog.controllers;

import lombok.AllArgsConstructor;
import org.blog.blog.dto.CommentDTO;
import org.blog.blog.dto.PostDTO;
import org.blog.blog.entities.Comment;
import org.blog.blog.entities.Post;
import org.blog.blog.entities.User;
import org.blog.blog.repositories.CommentRepository;
import org.blog.blog.repositories.PostRepository;
import org.blog.blog.repositories.UserRepository;
import org.blog.blog.servises.PostService;
import org.blog.blog.servises.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {


    private PostService postService;

    @GetMapping("/blog")
    public ResponseEntity<List<PostDTO>> blog() {
        return mappingResponseList(postService.getPosts());
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<PostDTO> blogDetails(@PathVariable("id") int id) {
        return mappingResponse(postService.getById(id));
    }

    @PostMapping("/blog/add")
    public ResponseEntity<PostDTO> handlePost(@RequestBody PostDTO postDTO) {
        PostDTO post =  postService.addPost(postDTO);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @PostMapping("/blog/{id}/leave-comment")
    public ResponseEntity<CommentDTO> leaveComment(@RequestBody CommentDTO commentDTO) {
        return mappingResponse(postService.addComment(commentDTO));
    }


    private ResponseEntity<PostDTO> mappingResponse(PostDTO postDTO) {
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }
    private ResponseEntity<CommentDTO> mappingResponse(CommentDTO commentDTO) {
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
    private ResponseEntity<List<PostDTO>> mappingResponseList(List<PostDTO> posts) {
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}

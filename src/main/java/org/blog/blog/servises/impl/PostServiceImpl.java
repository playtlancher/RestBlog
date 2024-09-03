package org.blog.blog.servises.impl;

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
import org.blog.blog.servises.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public PostDTO addPost(PostDTO postDTO) {
        User user = userRepository.findById(postDTO.getUserId()).get();
        Post post = postRepository.save(new Post(postDTO.getTitle(), postDTO.getSmallDescription(), postDTO.getText(), user));
        return modelMapper.map(post, PostDTO.class);

    }

    @Override
    public List<PostDTO> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }


    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId()).get();
        User user = userRepository.findById(commentDTO.getUserId()).get();
        Comment comment = commentRepository.save(new Comment(commentDTO.getText(), user, post));
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getComments(int postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getById(int id) {
        Post post = postRepository.findById(id).get();
        return modelMapper.map(post, PostDTO.class);
    }
}

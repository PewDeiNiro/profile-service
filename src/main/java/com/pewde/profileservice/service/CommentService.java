package com.pewde.profileservice.service;

import com.pewde.profileservice.entity.Comment;
import com.pewde.profileservice.entity.Post;
import com.pewde.profileservice.entity.User;
import com.pewde.profileservice.exception.CommentDoesNotBelongToUserException;
import com.pewde.profileservice.exception.CommentDoesNotExistsException;
import com.pewde.profileservice.exception.PostDoesNotExistsException;
import com.pewde.profileservice.exception.UserDoesNotExistsException;
import com.pewde.profileservice.repository.CommentRepository;
import com.pewde.profileservice.repository.PostRepository;
import com.pewde.profileservice.repository.UserRepository;
import com.pewde.profileservice.request.CreateCommentRequest;
import com.pewde.profileservice.request.DeleteCommentRequest;
import com.pewde.profileservice.request.EditCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment getCommentById(int id){
        return commentRepository.findById(id).orElseThrow(CommentDoesNotExistsException::new);
    }

    public List<Comment> getCommentsByPostId(int id){
        return postRepository.findById(id).orElseThrow(PostDoesNotExistsException::new).getComments();
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment createComment(CreateCommentRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Post post = postRepository.findById(request.getPostId()).orElseThrow(PostDoesNotExistsException::new);
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setPost(post);
        comment.setAuthor(user);
        return commentRepository.saveAndFlush(comment);
    }

    public Comment editComment(EditCommentRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Comment comment = commentRepository.findById(request.getCommentId()).orElseThrow(CommentDoesNotExistsException::new);
        if (!comment.getAuthor().equals(user)) {
            throw new CommentDoesNotBelongToUserException();
        }
        comment.setText(request.getText());
        return commentRepository.saveAndFlush(comment);
    }

    public ResponseEntity<HttpStatus> deleteComment(DeleteCommentRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Comment comment = commentRepository.findById(request.getCommentId()).orElseThrow(CommentDoesNotExistsException::new);
        if (!comment.getAuthor().equals(user)) {
            throw new CommentDoesNotBelongToUserException();
        }
        comment.getPost().getComments().remove(comment);
        comment.setPost(null);
        comment.setAuthor(null);
        commentRepository.delete(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.pewde.profileservice.service;

import com.pewde.profileservice.entity.Post;
import com.pewde.profileservice.entity.User;
import com.pewde.profileservice.entity.Wall;
import com.pewde.profileservice.exception.PostDoesNotBelongToUserException;
import com.pewde.profileservice.exception.PostDoesNotExistsException;
import com.pewde.profileservice.exception.UserDoesNotExistsException;
import com.pewde.profileservice.exception.WallDoesNotExistsException;
import com.pewde.profileservice.repository.PostRepository;
import com.pewde.profileservice.repository.UserRepository;
import com.pewde.profileservice.repository.WallRepository;
import com.pewde.profileservice.request.CreatePostRequest;
import com.pewde.profileservice.request.DeletePostRequest;
import com.pewde.profileservice.request.EditPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WallRepository wallRepository;

    public Post getPostById(int id){
        return postRepository.findById(id).orElseThrow(PostDoesNotExistsException::new);
    }

    public List<Post> getPostByWallId(int id){
        return wallRepository.findById(id).orElseThrow(WallDoesNotExistsException::new).getPosts();
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post createPost(CreatePostRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Wall wall = user.getWall();
        if (wall == null){
            wall = new Wall();
            user.setWall(wall);
        }
        Post post = new Post();
        post.setText(request.getText());
        post.setAuthor(user);
        post.setWall(wall);
        return postRepository.saveAndFlush(post);
    }

    public Post editPost(EditPostRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
        Post post = postRepository.findById(request.getPostId()).orElseThrow(PostDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        if (!post.getAuthor().equals(user)){
            throw new PostDoesNotBelongToUserException();
        }
        post.setText(request.getText());
        return postRepository.saveAndFlush(post);
    }

    public ResponseEntity<HttpStatus> deletePost(DeletePostRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Post post = postRepository.findById(request.getPostId()).orElseThrow(PostDoesNotExistsException::new);
        if (!post.getAuthor().equals(user)){
            throw new PostDoesNotBelongToUserException();
        }
        post.getWall().getPosts().remove(post);
        post.getAuthor().getPosts().remove(post);
        post.setWall(null);
        post.setAuthor(null);
        postRepository.delete(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

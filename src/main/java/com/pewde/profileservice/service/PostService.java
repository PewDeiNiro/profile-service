package com.pewde.profileservice.service;

import com.pewde.profileservice.entity.Group;
import com.pewde.profileservice.entity.Post;
import com.pewde.profileservice.entity.User;
import com.pewde.profileservice.entity.Wall;
import com.pewde.profileservice.enums.PostType;
import com.pewde.profileservice.enums.WallType;
import com.pewde.profileservice.exception.*;
import com.pewde.profileservice.repository.GroupRepository;
import com.pewde.profileservice.repository.PostRepository;
import com.pewde.profileservice.repository.UserRepository;
import com.pewde.profileservice.repository.WallRepository;
import com.pewde.profileservice.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WallRepository wallRepository;

    @Autowired
    private GroupRepository groupRepository;

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
        Wall wall;
        if (request.getWallId() == -1){
            wall = user.getWall();
            if (wall == null){
                wall = new Wall();
                wall.setType(WallType.USER_WALL);
                user.setWall(wall);
            }
        }
        else{
            wall = wallRepository.findById(request.getWallId()).orElseThrow(WallDoesNotExistsException::new);
        }
        if (wall.getType().equals(WallType.GROUP_WALL)){
            Group group = groupRepository.findByWall(wall).orElseThrow(GroupDoesNotExistsException::new);
            if (!group.getAdmins().contains(user)){
                throw new UserDoesNotAdminException();
            }
        }
        Post post = new Post();
        post.setText(request.getText());
        post.setType(PostType.POST);
        post.setWall(wall);
        return postRepository.saveAndFlush(post);
    }

    public Post editPost(EditTargetRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Post post = postRepository.findById(request.getTargetId()).orElseThrow(PostDoesNotExistsException::new);
        Wall wall = post.getWall();
        if (wall.getType().equals(WallType.GROUP_WALL)){
            Group group = groupRepository.findByWall(wall).orElseThrow(GroupDoesNotExistsException::new);
            if (!group.getAdmins().contains(user)){
                throw new UserDoesNotAdminException();
            }
        }
        else{
            if (!wall.getUser().equals(user)){
                throw new PostDoesNotBelongToUserException();
            }
        }
        post.setText(request.getText());
        return postRepository.saveAndFlush(post);
    }

    public ResponseEntity<HttpStatus> deletePost(DeleteTargetRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Post post = postRepository.findById(request.getTargetId()).orElseThrow(PostDoesNotExistsException::new);
        Wall wall = post.getWall();
        if (wall.getType().equals(WallType.GROUP_WALL)){
            Group group = groupRepository.findByWall(wall).orElseThrow(GroupDoesNotExistsException::new);
            if (!group.getAdmins().contains(user)){
                throw new UserDoesNotAdminException();
            }
        }
        else{
            if (!wall.getUser().equals(user)){
                throw new PostDoesNotBelongToUserException();
            }
        }
        wall.getPosts().remove(post);
        post.setWall(null);
        deleteReposts(post);
        postRepository.delete(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void deleteReposts(Post post){
        for (Post repost : post.getReposts()){
            if (!repost.getReposts().isEmpty()){
                deleteReposts(repost);
            }
            repost.getWall().getPosts().remove(repost);
            repost.setWall(null);
            postRepository.delete(repost);
        }
    }

    public Post ratePost(RateTargetRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Post post = postRepository.findById(request.getTargetId()).orElseThrow(PostDoesNotExistsException::new);
        List<User> likes = post.getLikes();
        if (likes.contains(user)){
            likes.remove(user);
        }
        else{
            likes.add(user);
        }
        return postRepository.saveAndFlush(post);
    }

    //TODO вынести логику создания постов в мапперы
    public Post makeRepost(MakeRepostRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        Wall wall = user.getWall();
        if (wall == null){
            wall = new Wall();
            wall.setType(WallType.USER_WALL);
            user.setWall(wall);
        }
        Post originalPost = postRepository.findById(request.getTargetId()).orElseThrow(PostDoesNotExistsException::new), repost = new Post();
        repost.setType(PostType.REPOST);
        repost.setComments(new ArrayList<>());
        repost.setText(request.getText() == null ? "" : request.getText());
        repost.setLikes(new ArrayList<>());
        repost.setWall(wall);
        repost.setOriginalPost(originalPost);
        return postRepository.saveAndFlush(repost);
    }

}

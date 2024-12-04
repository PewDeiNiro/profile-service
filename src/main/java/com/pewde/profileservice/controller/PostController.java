package com.pewde.profileservice.controller;

import com.pewde.profileservice.entity.Post;
import com.pewde.profileservice.request.*;
import com.pewde.profileservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PostController", description = "Все операции взаимодейсвтия с постами")
@Validated
@RestController
@RequestMapping("/api/profile/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "Получение поста по уникальному идентификатору")
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @Operation(summary = "Получение всех постов по уникальному идентификатору стены")
    @GetMapping("/wall/{id}")
    public List<Post> getPostsByWallId(@PathVariable int id) {
        return postService.getPostByWallId(id);
    }

    @Operation(summary = "Получение списка всех постов в системе")
    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @Operation(summary = "Создание нового поста")
    @PostMapping("/create")
    public Post createPost(@RequestBody @Valid CreatePostRequest request,
                           @RequestHeader("Authorization") String token) {
        return postService.createPost(request, token);
    }

    @Operation(summary = "Редактирование поста")
    @PutMapping("/edit")
    public Post editPost(@RequestBody @Valid EditPostRequest request,
                         @RequestHeader("Authorization") String token) {
        return postService.editPost(request, token);
    }

    @Operation(summary = "Удаление поста")
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deletePost(@RequestBody @Valid DeletePostRequest request,
                                                 @RequestHeader("Authorization") String token) {
        return postService.deletePost(request, token);
    }

    @Operation(summary = "Операция оценки нравится на пост и ее снятия с него")
    @PostMapping("/rate")
    public Post ratePost(@RequestBody @Valid RatePostRequest request,
                         @RequestHeader("Authorization") String token) {
        return postService.ratePost(request, token);
    }

    @Operation(summary = "Сделать репост")
    @PostMapping("/repost")
    public Post makeRepost(@RequestBody @Valid MakeRepostRequest request,
                           @RequestHeader("Authorization") String token) {
        return postService.makeRepost(request, token);
    }

}

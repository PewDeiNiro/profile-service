package com.pewde.profileservice.controller;

import com.pewde.profileservice.entity.Comment;
import com.pewde.profileservice.request.CreateCommentRequest;
import com.pewde.profileservice.request.DeleteCommentRequest;
import com.pewde.profileservice.request.EditCommentRequest;
import com.pewde.profileservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CommentController", description = "Все операции связанные с комментариями")
@Validated
@RestController
@RequestMapping("/api/profile/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Получение комментария по уникальному идентификатору")
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable int id){
        return commentService.getCommentById(id);
    }

    @Operation(summary = "Получение всех комментариев, относящихся к посту")
    @GetMapping("/post/{id}")
    public List<Comment> getCommentsByPostId(@PathVariable int id){
        return commentService.getCommentsByPostId(id);
    }

    @Operation(summary = "Получение всех комментариев в системе")
    @GetMapping("/posts")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @Operation(summary = "Создание комментария к посту")
    @PostMapping("/create")
    public Comment createComment(@RequestBody @Valid CreateCommentRequest request,
                                 @RequestHeader("Authorization") String token){
        return commentService.createComment(request, token);
    }

    @Operation(summary = "Редактирование комментария")
    @PutMapping("/edit")
    public Comment editComment(@RequestBody @Valid EditCommentRequest request,
                               @RequestHeader("Authorization") String token){
        return commentService.editComment(request, token);
    }

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteComment(@RequestBody @Valid DeleteCommentRequest request,
                                                    @RequestHeader("Authorization") String token){
        return commentService.deleteComment(request, token);
    }

}

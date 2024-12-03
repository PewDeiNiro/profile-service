package com.pewde.profileservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PostController", description = "Все операции взаимодейсвтия с постами")
@Validated
@RestController
@RequestMapping("/api/profile/posts")
public class PostController {



}

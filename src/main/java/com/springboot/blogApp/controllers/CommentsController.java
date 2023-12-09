package com.springboot.blogApp.controllers;

import com.springboot.blogApp.entities.Comments;
import com.springboot.blogApp.payloads.CommentsDto;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.payloads.UserDto;
import com.springboot.blogApp.service.CommentsService;
import com.springboot.blogApp.service.PostService;
import com.springboot.blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/Comments")
public class CommentsController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    CommentsService commentsService;
    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<CommentsDto> createComments(@RequestBody CommentsDto commentsDto, @PathVariable("userId")int userId, @PathVariable("postId")int postId)
    {
        UserDto userDto = userService.getUserById(userId);
        PostDto postDto = postService.getPostById(postId);
        commentsDto.setUser(userDto);
        commentsDto.setPost(postDto);
        CommentsDto commentsDto1 = commentsService.createComment(commentsDto);
        return new ResponseEntity<>(commentsDto1, HttpStatus.CREATED);
    }
//    @GetMapping("/")
//    public ResponseEntity<Set<CommentsDto>> getComments()
//    {

    //}

}

package com.springboot.blogApp.payloads;

import com.springboot.blogApp.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsDto {
    private int id;
    private String content;
    UserDto user;
    PostDto post;
}

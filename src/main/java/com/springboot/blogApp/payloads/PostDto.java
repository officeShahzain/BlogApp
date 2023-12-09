package com.springboot.blogApp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int id;
    private String title;
    private String content;
    private String image ;
    private Date addDate;
    private UserDto user;
    private CategoryDto category;
}

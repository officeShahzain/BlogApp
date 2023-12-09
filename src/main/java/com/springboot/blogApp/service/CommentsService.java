package com.springboot.blogApp.service;

import com.springboot.blogApp.payloads.CommentsDto;

import java.util.List;

public interface CommentsService {

    CommentsDto createComment(CommentsDto commentsDto);
    CommentsDto updateComment(CommentsDto commentsDto);
    void deleteComment(int commentId);
    List<CommentsDto> getAllComments();
    CommentsDto getCommentByUserId(int commentId);
    List<CommentsDto> getCommentsByUser(int userId);

}

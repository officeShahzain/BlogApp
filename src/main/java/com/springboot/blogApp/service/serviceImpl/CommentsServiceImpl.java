package com.springboot.blogApp.service.serviceImpl;

import com.springboot.blogApp.dao.CommentsRepository;
import com.springboot.blogApp.entities.Comments;
import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.payloads.CommentsDto;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.payloads.UserDto;
import com.springboot.blogApp.service.CommentsService;
import com.springboot.blogApp.service.PostService;
import com.springboot.blogApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CommentsDto createComment(CommentsDto commentsDto) {
        Comments comments = commentsRepository.save(modelMapper.map(commentsDto,Comments.class));
        return this.modelMapper.map(comments,CommentsDto.class);
    }

    @Override
    public CommentsDto updateComment(CommentsDto commentsDto) {
        return null;
    }

    @Override
    public void deleteComment(int commentId) {

    }

    @Override
    public List<CommentsDto> getAllComments() {
        return null;
    }

    @Override
    public CommentsDto getCommentByUserId(int commentId) {
        return null;
    }

    @Override
    public List<CommentsDto> getCommentsByUser(int userId) {
        return null;
    }
}

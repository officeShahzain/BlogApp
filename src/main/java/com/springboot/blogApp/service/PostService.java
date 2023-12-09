package com.springboot.blogApp.service;

import com.springboot.blogApp.entities.Post;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,int userId, int categoryId);
    PostDto updatePost (PostDto postDto, int postId);
    void deletePost (int postId);

    List<PostDto> getPost();

    PostDto getPostById (int postId);

    List<PostDto> getPostByUserId(int userId);
    List<PostDto> getPostByCategory(int categoryId);
    List<PostDto> getPostByPageWise(int pageNumber, int pageSize);
    PostResponse getPostsWithPostResponse(int pageNumber, int pageSize);

    public List<PostDto> searchingPost(String title);
    public List<PostDto> searchPost(String title);

}

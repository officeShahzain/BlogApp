package com.springboot.blogApp.controllers;

import com.springboot.blogApp.exception.ApiResponse;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.payloads.PostResponse;
import com.springboot.blogApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post/")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/{userId}/{categoryId}")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto,@PathVariable("userId")int userId,@PathVariable("categoryId")int categoryId)
    {
        PostDto addPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(addPost, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") int postId)
    {
        PostDto update = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(update,HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") int postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("this post no more in our db",true), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPost()
    {
       List<PostDto> posts =  this.postService.getPost();
       return new ResponseEntity<>(posts,HttpStatus.FOUND);
    }
    @GetMapping("/{postId}")
    public  ResponseEntity<PostDto> getPostByID(@PathVariable("postId")int postId)
    {
        return new ResponseEntity<>(this.postService.getPostById(postId), HttpStatus.FOUND);
    }
    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId")int categoryId)
    {
        List<PostDto> getPosts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(getPosts, HttpStatus.FOUND);
    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId")int userId)
    {
        List<PostDto> posts = this.postService.getPostByUserId(userId);
        return  new ResponseEntity<>(posts,HttpStatus.FOUND);

    }
    @GetMapping("/postPage")
    public ResponseEntity<List<PostDto>> getListByPageWise(
            @RequestParam (value = "pageNumber", defaultValue = "0", required = false)int pageNumber,
            @RequestParam (value = "pageSize", defaultValue = "5", required = false)int pageSize
            )
    {
        List<PostDto> getPages = this.postService.getPostByPageWise(pageNumber,pageSize);
        return new ResponseEntity<>(getPages, HttpStatus.FOUND);
    }
    @GetMapping("/pageResponse")
    public ResponseEntity<PostResponse> getPostWithResponse(@RequestParam( value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false)int pageSize)
    {
        return new ResponseEntity<>(this.postService.getPostsWithPostResponse(pageNo, pageSize),HttpStatus.OK);
    }
    /*
    @GetMapping("/search/{searchTitle}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("searchTitle") String searchTitle)
    {
        return new ResponseEntity<>(this.postService.searchingPost(searchTitle),HttpStatus.OK);
    }
    */
    @GetMapping("search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostWithKeyword(@PathVariable String keyword)
    {
        return new ResponseEntity<>(this.postService.searchPost(keyword),HttpStatus.OK);
    }

}

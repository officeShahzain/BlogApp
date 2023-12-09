package com.springboot.blogApp.controllers;

import com.springboot.blogApp.dao.PostRepository;
import com.springboot.blogApp.entities.Post;
import com.springboot.blogApp.exception.ResourceNotFound;
import com.springboot.blogApp.payloads.FileResponse;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.service.FileService;
import com.springboot.blogApp.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("file/")
public class FileController {
    @Value("${project.image}")
    String path;
    @Autowired
    FileService fileService;
    @Autowired
    PostService postService;



    @PostMapping("/upload/{postId}")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam ("file")MultipartFile file,@PathVariable("postId")int postId)
    {
        PostDto postDto = postService.getPostById(postId);
        String name = fileService.uploadFile(path,file);
        postDto.setImage(name);
        postDto = postService.updatePost(postDto,postId);

       // Post post1 = postRepository.findById(postId).orElseThrow(()->new ResourceNotFound("Post","poseID : ",postId));
        //post1.setImage(name);
       // postRepository.save(post1);
        return new ResponseEntity<>(new FileResponse(name,"Image Uploaded Successfully"), HttpStatus.OK);
    }
    @GetMapping("/download/{imageName}")
    public void downloadImage(@PathVariable("imageName")String imageName, HttpServletResponse response) throws IOException
    {

        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}

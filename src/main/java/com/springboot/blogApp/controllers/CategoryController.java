package com.springboot.blogApp.controllers;

import com.springboot.blogApp.entities.Category;
import com.springboot.blogApp.entities.Post;
import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.exception.ApiResponse;
import com.springboot.blogApp.exception.ResourceNotFound;
import com.springboot.blogApp.payloads.CategoryDto;
import com.springboot.blogApp.payloads.PostDto;
import com.springboot.blogApp.payloads.UserDto;
import com.springboot.blogApp.service.CategoryService;
import com.springboot.blogApp.service.PostService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    //add category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto)
    {
        CategoryDto newCategory = this.categoryService.addCategory(categoryDto);
//        if(newCategory.equals(null))
//        {
//             throw  new ResourceNotFound("List is ","Empty", 1);
//        }
        return  new ResponseEntity<>(newCategory, HttpStatus.FOUND);
    }

    //get list of category
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory()
    {
        List<CategoryDto> categoryDtos = this.categoryService.getCategory();
        if(categoryDtos.isEmpty())
        {
            throw  new ResourceNotFound("List is ","Empty", 1);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.NOT_FOUND);
    }

    //get category by id
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") int categoryId)
    {
        CategoryDto postDto = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(postDto, HttpStatus.FOUND);

    }
    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getPostByUserId (@RequestBody UserDto userDto,@PathVariable("categoryId") int id)
    {
        CategoryDto category = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("this user no more in our db",true),HttpStatus.OK);

    }




}

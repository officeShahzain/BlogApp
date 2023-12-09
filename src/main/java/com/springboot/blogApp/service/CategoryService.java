package com.springboot.blogApp.service;

import com.springboot.blogApp.entities.Category;
import com.springboot.blogApp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    // add category
    public CategoryDto addCategory(CategoryDto categoryDto);

    //update category
    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    //delete category
    void deleteCategory(Integer categoryId);

    // get category by id

    CategoryDto getCategoryById(Integer categoryId);

    //list of category
    List<CategoryDto> getCategory();

}

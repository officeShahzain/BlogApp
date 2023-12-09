package com.springboot.blogApp.service.serviceImpl;

import com.springboot.blogApp.dao.CategoryRepository;
import com.springboot.blogApp.entities.Category;
import com.springboot.blogApp.exception.ResourceNotFound;
import com.springboot.blogApp.payloads.CategoryDto;
import com.springboot.blogApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    //add category
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category addNewCategory = this.categoryRepository.save(this.modelMapper.map(categoryDto, Category.class));
        System.out.println(addNewCategory);
        System.out.println(addNewCategory.getType());

        CategoryDto abc =  this.modelMapper.map(addNewCategory, CategoryDto.class);

        return abc;//this.modelMapper.map(addNewCategory, CategoryDto.class);
    }


    //update category
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category getCategory = this.categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Update Category","Id : ",id));
        getCategory.setType(categoryDto.getType());
        getCategory.setTitle(categoryDto.getTitle());
        Category update = this.categoryRepository.save(getCategory);
        return this.modelMapper.map(update,CategoryDto.class);
    }



    //delete category
    @Override
    public void deleteCategory(Integer categoryId) {
        Category getCategory = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","Id : ", categoryId));
        this.categoryRepository.delete(getCategory);

    }


    //get by id
    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category getById = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category","Id",categoryId));

        return this.modelMapper.map(getById, CategoryDto.class);
    }
    //get list of category
    @Override
    public List<CategoryDto> getCategory() {
        List<Category> getAllCategory = this.categoryRepository.findAll();
        List<CategoryDto> categoryList = getAllCategory.stream().map((category)-> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryList;
    }
}

package com.saif.blog.blogApp.services;


import com.saif.blog.blogApp.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {


    //create

     CategoryDto createCategory(CategoryDto categoryDto);

    //update

    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);

    void deleteCategory(Integer  categoryId);

    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getAllCategories();




}

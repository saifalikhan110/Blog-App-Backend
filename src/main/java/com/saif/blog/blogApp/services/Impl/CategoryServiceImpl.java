package com.saif.blog.blogApp.services.Impl;

import com.saif.blog.blogApp.entities.Category;
import com.saif.blog.blogApp.exceptions.ResourceNotFoundException;
import com.saif.blog.blogApp.payloads.CategoryDto;
import com.saif.blog.blogApp.repositories.CategoryRepo;
import com.saif.blog.blogApp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category=this.categoryToCategoryDto(categoryDto);
        Category savedCategory=this.categoryRepo.save(category);


        return this.categoryDtoToCategory(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {

        Category category=this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","category id",id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category savedCat = this.categoryRepo.save(category);

        return this.modelMapper.map(savedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        this.categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));



        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = this.categoryRepo.findAll();

        List<CategoryDto> categoryDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());


        return categoryDtos;
    }

    public Category categoryToCategoryDto(CategoryDto categoryDto ){

        Category category=this.modelMapper.map(categoryDto,Category.class);

        return category;
    }

    public CategoryDto categoryDtoToCategory(Category category ){

        CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}

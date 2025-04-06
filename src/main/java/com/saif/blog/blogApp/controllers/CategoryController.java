package com.saif.blog.blogApp.controllers;


import com.saif.blog.blogApp.payloads.ApiResponse;
import com.saif.blog.blogApp.payloads.CategoryDto;
import com.saif.blog.blogApp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int id){


        return ResponseEntity.ok(this.categoryService.getCategory(id));


    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        return ResponseEntity.ok(this.categoryService.getAllCategories());


    }


    @PostMapping("/")
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);


        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);


    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable int id){



        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, id);


         return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestBody CategoryDto categoryDto ,@PathVariable int id){


        this.categoryService.deleteCategory(id);

        return new ResponseEntity(new ApiResponse("User Deleted Successfully ",true),HttpStatus.CREATED);




    }


}

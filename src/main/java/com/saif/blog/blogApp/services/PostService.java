package com.saif.blog.blogApp.services;

import com.saif.blog.blogApp.entities.Category;
import com.saif.blog.blogApp.entities.Post;
import com.saif.blog.blogApp.entities.User;
import com.saif.blog.blogApp.payloads.PostDto;
import com.saif.blog.blogApp.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer id);

    PostDto getPost(Integer id);

    PostResponse getAllPost(Integer pageNum, Integer pageSize,String sortBy,String sortDir);

    void deletePost(Integer id);

    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);
}

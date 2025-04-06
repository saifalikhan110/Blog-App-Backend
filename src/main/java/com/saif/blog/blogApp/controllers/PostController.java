package com.saif.blog.blogApp.controllers;

import com.saif.blog.blogApp.entities.Post;
import com.saif.blog.blogApp.payloads.ApiResponse;
import com.saif.blog.blogApp.payloads.PostDto;
import com.saif.blog.blogApp.payloads.PostResponse;
import com.saif.blog.blogApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId) {


        this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostDto>(HttpStatus.CREATED);
    }


    @GetMapping("/category/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer id){

        List<PostDto> postByCategory = this.postService.getPostByCategory(id);


        return new ResponseEntity<List<PostDto>>( postByCategory,HttpStatus.OK);
    }


    @GetMapping("/user/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer id){

        List<PostDto> postUser = this.postService.getPostByUser(id);


        return new ResponseEntity<List<PostDto>>(postUser,HttpStatus.OK);
    }

    @GetMapping("/allPost")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNum",defaultValue ="0",required = false)Integer pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {


        PostResponse postResponse = this.postService.getAllPost(pageNum, pageSize,sortBy,sortDir);

        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id){

        PostDto post = this.postService.getPost(id);

        return new ResponseEntity<>(post,HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePost(@PathVariable Integer id){

        this.postService.deletePost(id);
        return new ApiResponse("The Post is Successfully Deleted",true);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@RequestBody PostDto postDto,@PathVariable Integer id){

        PostDto postDto1 = this.postService.updatePost(postDto, id);

        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> search(@PathVariable String keyword){

        List<PostDto> searchedPost = this.postService.searchPost(keyword);

        return new ResponseEntity<List<PostDto>>(searchedPost,HttpStatus.OK);
    }
}

package com.saif.blog.blogApp.controllers;

import com.saif.blog.blogApp.payloads.ApiResponse;
import com.saif.blog.blogApp.payloads.CommentDto;
import com.saif.blog.blogApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    @PostMapping("/createComment/{id}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer id){

        CommentDto comment = this.commentService.createComment(commentDto, id);

        return new ResponseEntity<CommentDto>(HttpStatus.CREATED);


    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){

        this.commentService.deleteComment(id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Succesfully",true),HttpStatus.OK);
    }
}

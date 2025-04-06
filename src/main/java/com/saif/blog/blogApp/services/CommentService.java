package com.saif.blog.blogApp.services;

import com.saif.blog.blogApp.payloads.CommentDto;
import com.saif.blog.blogApp.payloads.PostDto;

public interface CommentService {


    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer id);

}

package com.saif.blog.blogApp.services.Impl;

import com.saif.blog.blogApp.entities.Comment;
import com.saif.blog.blogApp.entities.Post;
import com.saif.blog.blogApp.exceptions.ResourceNotFoundException;
import com.saif.blog.blogApp.payloads.CommentDto;
import com.saif.blog.blogApp.payloads.PostDto;
import com.saif.blog.blogApp.repositories.CommentRepo;
import com.saif.blog.blogApp.repositories.PostRepo;
import com.saif.blog.blogApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));

        Comment comment=this.modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedCommet = this.commentRepo.save(comment);
        return this.modelMapper.map(savedCommet,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {

        Comment comment=this.commentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment","commentId",id));

        this.commentRepo.delete(comment);

    }
}

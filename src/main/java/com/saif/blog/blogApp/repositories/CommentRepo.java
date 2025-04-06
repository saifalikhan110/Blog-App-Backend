package com.saif.blog.blogApp.repositories;

import com.saif.blog.blogApp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<Comment,Integer> {
}

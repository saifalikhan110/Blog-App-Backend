package com.saif.blog.blogApp.repositories;

import com.saif.blog.blogApp.entities.Category;
import com.saif.blog.blogApp.entities.Post;
import com.saif.blog.blogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> searchByTitle(String title);
}

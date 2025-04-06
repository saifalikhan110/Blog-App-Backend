package com.saif.blog.blogApp.repositories;

import com.saif.blog.blogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}

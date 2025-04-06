package com.saif.blog.blogApp.services;

import com.saif.blog.blogApp.payloads.UserDto;

import java.util.List;

public interface UserService {


    UserDto createUser(UserDto userDto);
    UserDto updateUSer(UserDto userDto,Integer userId);
    UserDto getUserById(Integer id);
    List<UserDto>getAllUsers();
    void delete(Integer userId);


}

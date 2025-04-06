package com.saif.blog.blogApp.controllers;

import com.saif.blog.blogApp.payloads.ApiResponse;
import com.saif.blog.blogApp.payloads.UserDto;
import com.saif.blog.blogApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto user = this.userService.createUser(userDto);

        return new ResponseEntity<>(user,HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(UserDto userDto){



        return ResponseEntity.ok(this.userService.getAllUsers());


    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId")int uId){


        return ResponseEntity.ok(this.userService.getUserById(uId));



    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto,@PathVariable int id){

        UserDto userDto1 = this.userService.updateUSer(userDto, id);

        return ResponseEntity.ok(userDto1);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@Valid  @RequestBody UserDto userDto,@PathVariable int id){

        this.userService.delete(id);

        return new ResponseEntity(new ApiResponse("User Deleted Successfully ",true),HttpStatus.CREATED);


    }

}

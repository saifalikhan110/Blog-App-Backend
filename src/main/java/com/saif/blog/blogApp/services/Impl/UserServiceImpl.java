package com.saif.blog.blogApp.services.Impl;

import com.saif.blog.blogApp.entities.User;
import com.saif.blog.blogApp.exceptions.ResourceNotFoundException;
import com.saif.blog.blogApp.payloads.UserDto;
import com.saif.blog.blogApp.repositories.UserRepo;
import com.saif.blog.blogApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUSer(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("User","id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer id) {

        User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {


        List<User> user = this.userRepo.findAll();
        List<UserDto> collect = user.stream().map(user1 -> this.userToDto(user1)).collect(Collectors.toList());


        return collect;
    }

    @Override
    public void delete(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));

        this.userRepo.delete(user);
    }


    public User dtoToUser(UserDto userDto){

        User user=this.modelMapper.map(userDto,User.class);
        return user;

    }

    public UserDto userToDto(User user){

        UserDto userDto=this.modelMapper.map(user,UserDto.class);

        return userDto;
    }



//
//    public User dtoToUser(UserDto userDto){
//
//        User user=new User();
//
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        return user;
//
//
//    }
//
//
//    public UserDto userToDto(User user){
//
//
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
//
//        return userDto;
//
//
//    }


}

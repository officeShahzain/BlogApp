package com.springboot.blogApp.service;

import com.springboot.blogApp.payloads.UserDto;

import java.util.List;

public interface UserService {

    //create User
    public UserDto createUser(UserDto userDto);

    //UpdateUser
    public UserDto updateUser(UserDto userDto, int userId);

    //get all users
    public List<UserDto> getAllUsers();

    //get user by id
    public UserDto getUserById(int userId);

    //delete user by id
    public void delete(int userId);



}

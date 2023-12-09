package com.springboot.blogApp.controllers;

import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.exception.ApiResponse;
import com.springboot.blogApp.payloads.UserDto;
import com.springboot.blogApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //get all users mapping
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> getUsersList = this.userService.getAllUsers();
        return new ResponseEntity<>(getUsersList, HttpStatus.FOUND);
    }

    // get specific user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSpecificUser(@PathVariable("userId") int userId)
    {
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.NOT_FOUND);
    }
    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int userId)
    {
        UserDto updateUserValues = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updateUserValues, HttpStatus.OK);
    }
    //new user
    @PostMapping("/public")
    public  ResponseEntity<UserDto> newUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto addNewUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(addNewUser,HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")int id)
    {
        this.userService.delete(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("this user no more in our db",true),HttpStatus.FOUND);

    }
}

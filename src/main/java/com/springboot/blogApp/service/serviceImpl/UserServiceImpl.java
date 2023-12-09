package com.springboot.blogApp.service.serviceImpl;

import com.springboot.blogApp.dao.UserRepository;
import com.springboot.blogApp.entities.User;
import com.springboot.blogApp.exception.ApiResponse;
import com.springboot.blogApp.exception.ResourceNotFound;
import com.springboot.blogApp.payloads.UserDto;
import com.springboot.blogApp.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        System.out.println(userDto);
        User newUser = this.modelMapper.map(userDto,User.class);
        newUser = this.userRepository.save(newUser);
        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(@Valid UserDto userDto, int userId) {
        User userAvailable = this.userRepository.findById(userId).orElseThrow(() ->new ResourceNotFound("Update user","userId : ",userId));

        //set values in  user entity
        userAvailable.setName(userDto.getName());
        userAvailable.setEmail(userDto.getEmail());
        userAvailable.setPassword(userDto.getPassword());
        this.userRepository.save(userAvailable);
        UserDto updateAvailableUser = this.modelMapper.map(userAvailable, UserDto.class);

        return updateAvailableUser;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> findAllUsers= this.userRepository.findAll();
        if(findAllUsers.isEmpty())
        {
            throw new RuntimeException("List is Empty");
        }
        List<UserDto> getListOfUsers = findAllUsers.stream().map((user)->this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return getListOfUsers;
    }

    @Override
    public UserDto getUserById(int userId) {
        User checkUser = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFound("get user by id ","user ID :",userId));
        return this.modelMapper.map(checkUser, UserDto.class);
    }

    @Override
    public void delete(int userId) {
        User findUser = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("Users  ,","UserId : ", userId));
        this.userRepository.delete(findUser);
    }
}

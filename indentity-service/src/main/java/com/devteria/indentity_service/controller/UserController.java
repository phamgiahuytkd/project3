package com.devteria.indentity_service.controller;


import com.devteria.indentity_service.dto.request.ApiResponse;
import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.entity.User;
import com.devteria.indentity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userID}")
    User getUser(@PathVariable("userID") String userID){
        return userService.getUser(userID);
    }

    @PutMapping("/{userID}")
    User updateUser(@PathVariable("userID") String userID, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userID, request);
    }

    @DeleteMapping("/{userID}")
    String deleteUser(@PathVariable String userID){
        userService.deleteUser(userID);
        return "User has been deleted";
    }



}

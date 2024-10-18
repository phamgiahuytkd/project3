package com.devteria.indentity_service.service;

import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.entity.User;
import com.devteria.indentity_service.exception.AppException;
import com.devteria.indentity_service.exception.ErrorCode;
import com.devteria.indentity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());


        return userRepository.save(user);
    }

    public void deleteUser(String userID){
        userRepository.deleteById(userID);
    }


    public List<User> getUsers(){
        return userRepository.findAll();
    }



    public  User getUser(String id){
        return userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
    }



    public User updateUser(String userID, UserUpdateRequest request){
        User user = getUser(userID);

        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }
}

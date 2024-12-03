package com.pewde.profileservice.service;

import com.pewde.profileservice.entity.User;
import com.pewde.profileservice.exception.UserAlreadyBlockedException;
import com.pewde.profileservice.exception.UserAlreadyUnblockedException;
import com.pewde.profileservice.exception.UserDoesNotExistsException;
import com.pewde.profileservice.exception.WrongOldPasswordException;
import com.pewde.profileservice.repository.UserRepository;
import com.pewde.profileservice.request.BlockOrUnblockUserRequest;
import com.pewde.profileservice.request.ChangePasswordRequest;
import com.pewde.profileservice.request.ChangeUsernameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public User blockUser(BlockOrUnblockUserRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new),
                block = userRepository.findById(request.getBlockId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        if (user.getBlocklist().contains(block)){
            throw new UserAlreadyBlockedException();
        }
        user.getBlocklist().add(block);
        return userRepository.saveAndFlush(user);
    }

    public User unblockUser(BlockOrUnblockUserRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new),
                block = userRepository.findById(request.getBlockId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        if (!user.getBlocklist().contains(block)){
            throw new UserAlreadyUnblockedException();
        }
        user.getBlocklist().remove(block);
        return userRepository.saveAndFlush(user);
    }

    public User changeUsername(ChangeUsernameRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        user.setUsername(request.getUsername());
        return userRepository.saveAndFlush(user);
    }

    public User changePassword(ChangePasswordRequest request, String token){
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserDoesNotExistsException::new);
//        AuthService.checkAuth(user, token);
        if (!user.getPassword().equals(request.getOldPassword())){
            throw new WrongOldPasswordException();
        }
        user.setPassword(request.getNewPassword());
        return userRepository.saveAndFlush(user);
    }

}

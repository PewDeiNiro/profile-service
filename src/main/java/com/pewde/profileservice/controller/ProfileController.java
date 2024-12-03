package com.pewde.profileservice.controller;

import com.pewde.profileservice.entity.User;
import com.pewde.profileservice.request.BlockOrUnblockUserRequest;
import com.pewde.profileservice.request.ChangePasswordRequest;
import com.pewde.profileservice.request.ChangeUsernameRequest;
import com.pewde.profileservice.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ProfileController", description = "Все операции связанные с профилем пользователя")
@Validated
@RestController
@RequestMapping("/api/profile/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Операция блокировки пользователя")
    @PostMapping("/block")
    public User blockUser(@RequestBody @Valid BlockOrUnblockUserRequest request,
                          @RequestHeader("Authorization") String token){
        return profileService.blockUser(request, token);
    }

    @Operation(summary = "Операция разблокировки пользователя")
    @PostMapping("/unblock")
    public User unblockUser(@RequestBody @Valid BlockOrUnblockUserRequest request,
                            @RequestHeader("Authorization") String token){
        return profileService.unblockUser(request, token);
    }

    @Operation(summary = "Операция смена имени пользователя")
    @PostMapping("/change/username")
    public User changeUsername(@RequestBody @Valid ChangeUsernameRequest request,
                               @RequestHeader("Authorization") String token){
        return profileService.changeUsername(request, token);
    }

    @PostMapping("/change/password")
    @Operation(summary = "Операция смены пароля")
    public User changePassword(@RequestBody @Valid ChangePasswordRequest request,
                               @RequestHeader("Authorization") String token){
        return profileService.changePassword(request, token);
    }

}

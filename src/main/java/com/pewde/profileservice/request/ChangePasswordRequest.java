package com.pewde.profileservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @NotEmpty
    @Schema(description = "Старый пароль пользователя")
    private String oldPassword;

    @NotEmpty
    @Schema(description = "Новый пароль пользователя")
    private String newPassword;

}

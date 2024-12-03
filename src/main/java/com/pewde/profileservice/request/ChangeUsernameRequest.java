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
public class ChangeUsernameRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @NotEmpty
    @Schema(description = "Новое имя пользователя")
    private String username;

}

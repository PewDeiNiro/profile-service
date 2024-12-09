package com.pewde.profileservice.request;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {

    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @Hidden
    private int wallId = -1;

    @NotEmpty
    @Schema(description = "Текст поста")
    private String text;

}

package com.pewde.profileservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPostRequest {

    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @Schema(description = "Уникальный идетификатор поста")
    private int postId;

    @NotEmpty
    @Schema(description = "Новый текст поста")
    private String text;

}

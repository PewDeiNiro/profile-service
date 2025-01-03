package com.pewde.profileservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @Schema(description = "Уникальный идентификатор пользователя")
    private int userId;

    @Schema(description = "Уникальный идентификатор поста")
    private int targetId;

    @NotEmpty
    @Schema(description = "Текст комментария")
    private String text;

}

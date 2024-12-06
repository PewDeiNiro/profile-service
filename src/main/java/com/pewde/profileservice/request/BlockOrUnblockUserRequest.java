package com.pewde.profileservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockOrUnblockUserRequest {

    @Schema(description = "Уникальный идентификатор пользователя, выполняющего операцию")
    private int userId;

    @Schema(description = "Уникальный идентификатор пользователя, над которым производится операция")
    private int targetId;

}

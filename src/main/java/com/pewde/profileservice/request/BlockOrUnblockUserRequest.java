package com.pewde.profileservice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockOrUnblockUserRequest {

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя, выполняющего операцию")
    private int userId;

    @NotNull
    @Schema(description = "Уникальный идентификатор пользователя, над которым производится операция")
    private int blockId;

}

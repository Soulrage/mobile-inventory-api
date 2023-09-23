package com.ourInventory.inventory.controller.operations;

import com.ourInventory.inventory.dto.JwtRequest;
import com.ourInventory.inventory.dto.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthOperations {

    @Operation(summary = "Создание jwt токена"
            , description = "Запрос создающий jwt токен для последующего взаимодействия" +
            "с сервером, время жизни - час")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = JwtResponse.class)))
    }
    )
    @PostMapping("/auth")
    ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request);

}

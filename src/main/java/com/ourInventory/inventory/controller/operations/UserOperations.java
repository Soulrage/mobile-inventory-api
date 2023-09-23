package com.ourInventory.inventory.controller.operations;

import com.ourInventory.inventory.dto.RegistrationUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserOperations {


    @Operation(summary = "Регистрирует юзера по запросу",
            description = "Отправляет запрос на сервер для регистрации юзера," +
                    "доступ имеет только админ")

    @PostMapping("/registration")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "регистрация успешна"),
            @ApiResponse(responseCode = "401", description = "такой пользователь уже существует")
    })
    ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto);

    @Operation(summary = "Удаляет пользователя по имени",
            description = "Удаляет пользователя по имени, имя передаётся" +
                    " как переменная пути")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "юзер успешно удалён"),
            @ApiResponse(responseCode = "401", description = "такого пользователя не существует")
    })
    @DeleteMapping("/{name}")
    ResponseEntity<?> deleteUser(@PathVariable String name);

}

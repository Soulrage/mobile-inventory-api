package com.ourInventory.inventory.controller;

import com.ourInventory.inventory.controller.operations.UserOperations;
import com.ourInventory.inventory.dto.RegistrationUserDto;
import com.ourInventory.inventory.service.userservice.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/users")
@AllArgsConstructor
@Controller
public class UserController implements UserOperations {

    AuthService authService;

    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        boolean created = authService.createNewUser(registrationUserDto);
        if (created) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deleteUser(String name) {
        if (authService.removeUser(name)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}

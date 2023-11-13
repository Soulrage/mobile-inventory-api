package com.ourInventory.inventory.controller;

import com.ourInventory.inventory.controller.operations.AuthOperations;
import com.ourInventory.inventory.dto.JwtRequestDTO;
import com.ourInventory.inventory.dto.JwtResponseDTO;
import com.ourInventory.inventory.service.userservice.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/authentication")
@Slf4j
public class AuthController implements AuthOperations {

    private final AuthService authService;


    public ResponseEntity<?> createAuthToken(JwtRequestDTO authRequest) {
        Optional<JwtResponseDTO> jwtResponse = authService.createAuthToken(authRequest);
        if (jwtResponse.isPresent()) {
            return ResponseEntity.ok(jwtResponse.get());
        }
        return ResponseEntity.notFound().build();

    }


}

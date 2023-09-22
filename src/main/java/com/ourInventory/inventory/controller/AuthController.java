package com.ourInventory.inventory.controller;

import com.ourInventory.inventory.dto.JwtRequest;
import com.ourInventory.inventory.dto.RegistrationUserDto;
import com.ourInventory.inventory.service.itemservice.ItemIndexService;
import com.ourInventory.inventory.service.userservice.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/open")
@Slf4j
public class AuthController {

    private final AuthService authService;

    private final ItemIndexService indexService;

    @GetMapping("/test")
    public ResponseEntity<?> tester() {
        return ResponseEntity.ok(indexService.listItems());
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }

}

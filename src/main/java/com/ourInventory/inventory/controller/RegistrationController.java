package com.ourInventory.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Registration")
public class RegistrationController {
    @PostMapping
    public ResponseEntity<Boolean> registerObject(@RequestBody String[] args) {
        return ResponseEntity.ok(true);
    }
}

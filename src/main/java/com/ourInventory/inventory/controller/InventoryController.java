package com.ourInventory.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/Inventory")
public class InventoryController {
    @PostMapping
    public ResponseEntity<Boolean> inventoryObject(@RequestBody String[] args) {
        return ResponseEntity.ok(true);
    }
}

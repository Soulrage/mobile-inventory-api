package com.ourInventory.inventory.controller;

import com.ourInventory.inventory.controller.operations.InventoryOperations;
import com.ourInventory.inventory.dto.ItemDTO;
import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.mapper.ItemMapper;
import com.ourInventory.inventory.service.itemservice.ItemCreateService;
import com.ourInventory.inventory.service.itemservice.ItemDeleteService;
import com.ourInventory.inventory.service.itemservice.ItemIndexService;
import com.ourInventory.inventory.service.itemservice.ItemUpdateService;
import com.ourInventory.inventory.service.userservice.UserService;
import com.ourInventory.inventory.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
@AllArgsConstructor
public class InventoryController implements InventoryOperations {

    UserService userService;

    ItemCreateService createService;

    ItemIndexService indexService;

    ItemUpdateService updateService;

    ItemDeleteService deleteService;

    JwtUtils jwtUtils;


    @PatchMapping("/update")
    public ResponseEntity<?> updateHandler(
            @RequestBody ItemDTO itemDTO,
            @RequestHeader("Authorization") String fullToken) {
        return updateService.updateItem(itemDTO, fullToken) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> indexHandler(@PathVariable Long id) {
        Optional<ItemEntity> entity = indexService.itemById(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                ItemMapper.INSTANCE.toDto(entity.get())
        );
    }

    @PutMapping("/create")
    public ResponseEntity<?> createHandle(
            @RequestBody ItemDTO itemDTO,
            @RequestHeader("Authorization") String fulltoken) {
        boolean created = createService.createItem(itemDTO, fulltoken);
        if (!created) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHandler(@PathVariable Long id) {
        if (deleteService.removeById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> indexAllHandler() {
        List<ItemDTO> lst = indexService.listItems().stream().map(
                ItemMapper.INSTANCE::toDto).toList();
        return ResponseEntity.ok(lst);
    }


}

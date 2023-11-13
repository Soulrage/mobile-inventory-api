package com.ourInventory.inventory.controller;

import com.ourInventory.inventory.controller.operations.InventoryOperations;
import com.ourInventory.inventory.dto.ItemCreationRequestDTO;
import com.ourInventory.inventory.dto.ItemGetResponseDTO;
import com.ourInventory.inventory.dto.ItemUpdateRequestDTO;
import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.mapper.ItemMapper;
import com.ourInventory.inventory.service.itemservice.ItemCreateService;
import com.ourInventory.inventory.service.itemservice.ItemDeleteService;
import com.ourInventory.inventory.service.itemservice.ItemIndexService;
import com.ourInventory.inventory.service.itemservice.ItemUpdateService;
import com.ourInventory.inventory.service.itemservice.PictureService;
import com.ourInventory.inventory.service.userservice.UserService;
import com.ourInventory.inventory.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
@Slf4j
public class InventoryController implements InventoryOperations {

    UserService userService;

    ItemCreateService createService;

    ItemIndexService indexService;

    ItemUpdateService updateService;

    ItemDeleteService deleteService;

    PictureService pictureService;

    JwtUtils jwtUtils;


    @PatchMapping("/update")
    public ResponseEntity<?> updateHandler(
            @RequestBody ItemUpdateRequestDTO itemUpdateRequestDTO) {
        log.info("Updating  item: " + itemUpdateRequestDTO);
        return updateService.updateItem(itemUpdateRequestDTO) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> indexHandler(@PathVariable Long id) {
        Optional<ItemGetResponseDTO> opt = indexService.itemById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ItemGetResponseDTO dto = opt.get();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/create")
    public ResponseEntity<?> createHandle(
            @RequestBody ItemCreationRequestDTO itemCreationRequestDTO,
            @RequestHeader("Authorization") String fulltoken) {

        ItemEntity createdItem = createService.createItem(itemCreationRequestDTO, fulltoken);
        if (createdItem == null) return ResponseEntity.badRequest().build();
        ItemGetResponseDTO getResponseDTO = ItemMapper.INSTANCE.toGetResponseDTO(createdItem);
        log.info("Created item is: " + getResponseDTO);
        return ResponseEntity.ok(
                getResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHandler(@PathVariable Long id) {
        boolean removed = deleteService.removeById(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> indexAllHandler() {
        List<ItemGetResponseDTO> lst = indexService.listItems();
        return ResponseEntity.ok(lst);
    }


}

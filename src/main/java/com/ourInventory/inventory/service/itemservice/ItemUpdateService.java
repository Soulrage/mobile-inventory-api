package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.dto.ItemUpdateRequestDTO;
import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.mapper.ItemMapper;
import com.ourInventory.inventory.repository.ItemRepository;
import com.ourInventory.inventory.service.userservice.UserService;
import com.ourInventory.inventory.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemUpdateService {

    JwtUtils jwtUtils;

    UserService userService;

    ItemRepository repository;

    ItemIndexService indexService;

    PictureService pictureService;

    @Transactional
    public boolean updateItem(ItemUpdateRequestDTO dto) {
        Long id = dto.getId();
        Optional<ItemEntity> entity = repository.findById(id);
        if (entity.isEmpty()) return false;
        ItemEntity ent = entity.get();
        ItemMapper.INSTANCE.partialUpdate(dto, ent);
        byte[] bytes = dto.getBytes();
        if (bytes != null) {
            if (ent.getUuid() == null) {
                ent.setUuid(UUID.randomUUID());
            }
            pictureService.savePictureByUUID(ent.getUuid(), bytes);
        } else if (ent.getUuid() != null) {
            pictureService.deletePictureByUUID(ent.getUuid());
            ent.setUuid(null);
        }
        repository.save(ent);
        return true;

    }

}

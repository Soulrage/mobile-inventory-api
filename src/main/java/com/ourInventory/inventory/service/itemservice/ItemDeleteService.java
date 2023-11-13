package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemDeleteService {

    ItemRepository repository;

    PictureService pictureService;

    @Transactional
    public boolean removeById(Long id) {
        Optional<ItemEntity> entity = repository.findById(id);
        if (entity.isEmpty()) return false;
        ItemEntity item = entity.get();
        if (item.getUuid() != null) {
            pictureService.deletePictureByUUID(item.getUuid());
        }
        return removeById(item.getId());

    }

}

package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.dto.ItemUpdateRequestDTO;
import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.entity.UserEntity;
import com.ourInventory.inventory.mapper.ItemMapper;
import com.ourInventory.inventory.repository.ItemRepository;
import com.ourInventory.inventory.service.userservice.UserService;
import com.ourInventory.inventory.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemUpdateService {

    JwtUtils jwtUtils;

    UserService userService;

    ItemRepository repository;

    ItemIndexService indexService;


    public boolean updateItem(ItemUpdateRequestDTO dto, String fulltoken) {
        String username = jwtUtils.getUsername(fulltoken.substring(7));
        Optional<UserEntity> userId = userService.loadIdByName(username);

        Long id = dto.getId();
        Optional<ItemEntity> item = indexService.itemById(id);
        if (item.isEmpty()) return false;
        ItemEntity itemEntity = item.get();
        if (userId.isPresent()) {
            itemEntity.setUserId(userId.get().getId());
        }

        ItemMapper.INSTANCE.partialUpdate(dto, itemEntity);
        repository.save(itemEntity);
        return true;

    }

}

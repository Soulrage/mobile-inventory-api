package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.dto.ItemDTO;
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
public class ItemCreateService {

    JwtUtils jwtUtils;

    UserService userService;

    ItemRepository repository;

    ItemIndexService indexService;

    public boolean createItem(ItemDTO dto, String fulltoken) {
        String username = jwtUtils.getUsername(fulltoken.substring(7));
        Optional<UserEntity> userId = userService.loadIdByName(username);
        if (userId.isPresent()) {
            dto.setUserId(userId.get().getId());
        }
        ItemEntity entity = ItemMapper.INSTANCE.toEntity(dto);
        repository.save(entity);
        return true;
    }

}

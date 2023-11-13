package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.dto.ItemCreationRequestDTO;
import com.ourInventory.inventory.dto.UserInfoDTO;
import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.entity.UserEntity;
import com.ourInventory.inventory.mapper.ItemMapper;
import com.ourInventory.inventory.mapper.UserMapper;
import com.ourInventory.inventory.repository.ItemRepository;
import com.ourInventory.inventory.service.userservice.UserService;
import com.ourInventory.inventory.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemCreateService {

    JwtUtils jwtUtils;

    UserService userService;

    ItemRepository repository;

    ItemIndexService indexService;

    @Transactional()
    public ItemEntity createItem(ItemCreationRequestDTO dto, String fulltoken) {
        String username = jwtUtils.getUsername(fulltoken.substring(7));
        Optional<UserEntity> userEntity = userService.loadIdByName(username);
        ItemEntity entity = ItemMapper.INSTANCE.toEntity(dto);
        ItemMapper.INSTANCE.mergeUser(entity, userEntity);
        //TODO
        return repository.save(entity);

    }

}

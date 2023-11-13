package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.dto.ItemGetResponseDTO;
import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.mapper.ItemMapper;
import com.ourInventory.inventory.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemIndexService {

    ItemRepository repository;

    PictureService pictureService;

    @Transactional(readOnly = true)
    public Optional<ItemGetResponseDTO> itemById(Long id) {
        Optional<ItemEntity> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return Optional.empty();
        }
        ItemEntity entity = opt.get();
        ItemGetResponseDTO dto = ItemMapper.INSTANCE.toGetResponseDTO(entity);
        byte[] bytes = pictureService.getPictureByUUID(entity.getUuid());
        if (bytes != null) dto.setBytes(bytes);
        return Optional.of(dto);
    }


    @Transactional(readOnly = true)
    public List<ItemGetResponseDTO> listItems() {
        return repository.findAll().stream().map(ItemMapper.INSTANCE::toGetResponseDTO).toList();
    }

}

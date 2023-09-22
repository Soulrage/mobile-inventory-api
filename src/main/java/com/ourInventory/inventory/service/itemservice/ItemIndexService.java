package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ItemIndexService {

    ItemRepository repository;

    public Optional<ItemEntity> itemById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        if (id == null) return false;
        return repository.existsById(id);
    }

    public List<ItemEntity> listItems() {
        return repository.findAll();
    }

}

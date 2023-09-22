package com.ourInventory.inventory.service.itemservice;

import com.ourInventory.inventory.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemDeleteService {

    ItemRepository repository;

    public boolean removeById(Long id) {
        return repository.removeById(id);
    }

}

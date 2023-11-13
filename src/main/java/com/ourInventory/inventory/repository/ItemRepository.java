package com.ourInventory.inventory.repository;

import com.ourInventory.inventory.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    long removeById(Long id);




}

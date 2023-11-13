package com.ourInventory.inventory.repository;

import com.ourInventory.inventory.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);


    Optional<UserEntity> findByUsernameIgnoreCaseAllIgnoreCase(String username);



    long deleteByUsername(String username);


}

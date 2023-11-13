package com.ourInventory.inventory.mapper;

import com.ourInventory.inventory.dto.UserInfoDTO;
import com.ourInventory.inventory.entity.UserEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);






    UserInfoDTO toUserInfoDTO(UserEntity userEntity);


}

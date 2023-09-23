package com.ourInventory.inventory.mapper;

import com.ourInventory.inventory.dto.RegistrationUserDTO;
import com.ourInventory.inventory.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
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


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity toEntity(RegistrationUserDTO userRegistrationDTO);

    RegistrationUserDTO toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(RegistrationUserDTO userRegistrationDTO, @MappingTarget UserEntity userEntity);

}

package com.ourInventory.inventory.mapper;

import com.ourInventory.inventory.dto.ItemCreationRequestDTO;
import com.ourInventory.inventory.dto.ItemUpdateRequestDTO;
import com.ourInventory.inventory.entity.ItemEntity;
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
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(target = "userAdded", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ItemEntity partialUpdate(ItemUpdateRequestDTO dto, @MappingTarget ItemEntity entity);

    @Mapping(target = "userAdded", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "id", ignore = true)
    ItemEntity toEntity(ItemCreationRequestDTO itemCreationRequestDTO);

    ItemCreationRequestDTO toDto(ItemEntity itemEntity);

}

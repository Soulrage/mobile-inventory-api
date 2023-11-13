package com.ourInventory.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ourInventory.inventory.entity.ItemEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link ItemEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemGetResponseDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 300)
    private String itemName;

    @NotNull
    @Size(max = 30)
    private String inventoryNumber;

    @NotNull
    private Float price;

    private String userAddedUsername;

    private byte[] bytes;

}

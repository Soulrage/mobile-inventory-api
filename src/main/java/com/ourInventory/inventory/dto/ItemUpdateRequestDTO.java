package com.ourInventory.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ItemUpdateRequestDTO {

    private Long id;

    @NotNull
    @Size(max = 300)
    private String itemName;

    @NotNull
    @Size(max = 30)
    private String inventoryNumber;

    @NotNull
    private Float price;

}

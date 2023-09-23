package com.ourInventory.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCreationRequestDTO implements Serializable {
    @NotNull
    @Size(max = 300)
    private String itemName;

    @NotNull
    @Size(max = 30)
    private String inventoryNumber;

    @NotNull
    private Float price;
}

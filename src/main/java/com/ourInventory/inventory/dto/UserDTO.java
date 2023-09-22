package com.ourInventory.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.ourInventory.inventory.entity.UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String username;

    @NotNull
    @Size(max = 50)
    private String password;

}

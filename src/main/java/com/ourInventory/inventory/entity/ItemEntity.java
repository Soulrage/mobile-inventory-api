package com.ourInventory.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "inventory", schema = "item_schema")
@Accessors(chain = true)
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 300)
    @NotNull
    @Column(name = "item_name", nullable = false, length = 300)
    private String itemName;

    @Size(max = 30)
    @NotNull
    @Column(name = "inventory_number", nullable = false, length = 30)
    private String inventoryNumber;

    @NotNull
    @Column(name = "price", nullable = false)
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_added_id")
    private UserEntity userAdded;

    @Column(name = "pic_uuid")
    private UUID uuid;

}

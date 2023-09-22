package com.ourInventory.inventory;

import com.ourInventory.inventory.entity.ItemEntity;
import com.ourInventory.inventory.service.itemservice.ItemIndexService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @MockBean
    ItemIndexService indexService;

    @Autowired
    MockMvc mvc;

    @Test
    void indexTest() throws Exception {
        Mockito.when(indexService.itemById(1L)).thenReturn(
                Optional.of(mockItem())
        );
        mvc.perform(MockMvcRequestBuilders.get("/items/1")).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    private ItemEntity mockItem() {
        return new ItemEntity()
                .setUserId(10L)
                .setItemName("nigger")
                .setPrice(10F)
                .setInventoryNumber("123");
    }

}

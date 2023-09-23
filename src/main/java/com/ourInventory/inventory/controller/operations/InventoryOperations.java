package com.ourInventory.inventory.controller.operations;

import com.ourInventory.inventory.dto.ItemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface InventoryOperations {

    @Operation(description = "Обновляет данные о предмете")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Успешно обновлено"),
            @ApiResponse(responseCode = "404", description = "Предмет не найден")
    })
    @PatchMapping("/update")
    ResponseEntity<?> updateHandler(
            @RequestBody ItemDTO itemDTO,
            @RequestHeader("Authorization") String fullToken);

    @Operation(description = "Возвращает данные о предмете")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно обновлено",
                    content = @Content(schema = @Schema(
                            implementation = ItemDTO.class
                    ))),
            @ApiResponse(responseCode = "404", description = "Предмет не найден")
    })
    @GetMapping("/{id}")
    ResponseEntity<?> indexHandler(@PathVariable Long id);

    @Operation(description = "Создаёт новый предмет")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Успешно создано"),
            @ApiResponse(responseCode = "400", description = "Предмет уже существует")
    })
    @PutMapping("/create")
    ResponseEntity<?> createHandle(
            @RequestBody ItemDTO itemDTO,
            @RequestHeader("Authorization") String fulltoken);

    @Operation(description = "Удаляет предмет из базы")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Предмет не найден")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteHandler(@PathVariable Long id);

    @Operation(description = "Возвращает все предметы из базы")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Все найденные предметы",
                    content = @Content(array = @ArraySchema(arraySchema =
                    @Schema(implementation = List.class),
                    schema = @Schema(implementation = ItemDTO.class))))
    })
    @GetMapping("/list")
    ResponseEntity<?> indexAllHandler();

}

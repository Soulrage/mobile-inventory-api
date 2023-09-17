package com.ourInventory.inventory.controller;

import com.ourInventory.inventory.DatabaseConnection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/List")
public class ListController {
    @GetMapping
    public ResponseEntity<List<String>> getObjectList(@RequestParam("type") String objectType) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM inventory");
        while (resultSet.next()) {
            String columnValue = resultSet.getString("item_name");
            list.add(columnValue);
        }

        return ResponseEntity.ok(list);
    }
}

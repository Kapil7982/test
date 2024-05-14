package com.resturant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resturant.models.Table;
import com.resturant.service.TableService;

@RestController
@RequestMapping("/tables")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping
    public List<Table> getAllTables(@PathVariable(value = "restaurantId") Long restaurantId) {
        return tableService.getAllTables(restaurantId);
    }

    @PostMapping
    public ResponseEntity<Table> createTable(@PathVariable(value = "restaurantId") Long restaurantId,
                                             @RequestBody Table table) {
        Table createdTable = tableService.createTable(restaurantId, table);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTable);
    }
}

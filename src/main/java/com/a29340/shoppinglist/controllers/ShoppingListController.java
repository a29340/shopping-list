package com.a29340.shoppinglist.controllers;

import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.service.ShoppingListService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/shoppinglist")
public class ShoppingListController {

    @Autowired
    private ShoppingListService service;

    @PostMapping
    public ShoppingListDTO createShoppingList(@RequestBody ShoppingListDTO receivedShoppingList){
     return service.saveShoppingList(receivedShoppingList);
    }

    @GetMapping
    public Collection<ShoppingListDTO> getAllShoppingLists() {
        return service.getAllShoppingLists();
    }

    @DeleteMapping
    public ResponseEntity deleteShoppingList(@RequestBody ShoppingListDTO receivedShoppingList){
        if(service.deleteShoppingList(receivedShoppingList) != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

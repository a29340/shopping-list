package com.a29340.shoppinglist.controllers;

import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.ShoppingList;
import com.a29340.shoppinglist.repository.ShoppingListRepository;
import com.a29340.shoppinglist.service.ShoppingListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shoppinglist")
public class ShoppingListController {

    @Autowired
    private ShoppingListService service;

    @PostMapping
    public ShoppingListDTO createShoppingList(@RequestBody ShoppingListDTO receivedShoppingList){
     return service.createShoppingList(receivedShoppingList);
    }

    @GetMapping
    public Collection<ShoppingListDTO> getAllShoppingLists() {
        return service.getAllShoppingLists();
    }
}

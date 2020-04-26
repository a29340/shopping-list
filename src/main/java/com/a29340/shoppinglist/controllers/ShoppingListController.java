package com.a29340.shoppinglist.controllers;

import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.ShoppingList;
import com.a29340.shoppinglist.service.ShoppingListService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/shoppinglist")
public class ShoppingListController {

  @Autowired
  private ShoppingListService service;

  @PostMapping
  public ResponseEntity<ShoppingListDTO> createShoppingList(@RequestBody ShoppingListDTO receivedShoppingList) {
    try {
      return ResponseEntity.ok(service.saveShoppingList(receivedShoppingList));
    } catch (IOException e) {
      return ResponseEntity.badRequest().body(receivedShoppingList);
    }
  }

  @GetMapping
  public Collection<ShoppingListDTO> getAllShoppingLists() {
    return service.getAllShoppingLists();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShoppingListDTO> getShoppingList(@PathVariable Long id) {
    ShoppingListDTO shoppingListById = service.getShoppingListById(id);
    if (shoppingListById != null) {
      return ResponseEntity.ok(shoppingListById);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteShoppingList(@PathVariable Long id) {
    if (service.deleteShoppingList(id)) {
      return ResponseEntity.ok(id);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{id}/rename")
  public ResponseEntity<ShoppingListDTO> renameList(@PathVariable Long id, String newName) {
    ShoppingListDTO renamedShoppingListDTO = service.renameList(id, newName);
    if (renamedShoppingListDTO != null) {
      return ResponseEntity.ok(renamedShoppingListDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}

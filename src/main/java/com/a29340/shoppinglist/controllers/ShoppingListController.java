package com.a29340.shoppinglist.controllers;

import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.User;
import com.a29340.shoppinglist.repository.UserRepository;
import com.a29340.shoppinglist.service.ShoppingListService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/shopping/list")
public class ShoppingListController {

  @Autowired
  private ShoppingListService service;

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<ShoppingListDTO> createShoppingList(@RequestBody ShoppingListDTO receivedShoppingList,
                                                            Principal principal) {
    try {
      User user = userRepository.findByName(principal.getName())
          .orElse(new User(principal.getName()));
      return ResponseEntity.ok(service.saveShoppingList(receivedShoppingList, user));
    } catch (IOException e) {
      return ResponseEntity.badRequest().body(receivedShoppingList);
    }
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

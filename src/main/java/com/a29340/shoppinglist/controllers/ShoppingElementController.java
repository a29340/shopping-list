package com.a29340.shoppinglist.controllers;

import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.service.ShoppingElementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppingelement")
public class ShoppingElementController {

  @Autowired
  ShoppingElementService service;

  @GetMapping("/{id}")
  public ResponseEntity<ShoppingElementDTO> getShoppingElement(@PathVariable Long id) {
    ShoppingElementDTO shoppingElementDTO = service.getShoppingElementById(id);
    if(shoppingElementDTO != null) {
      return ResponseEntity.ok(shoppingElementDTO);
    }
    return ResponseEntity.notFound().build();
  }

}

package com.a29340.shoppinglist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.service.ShoppingCategoryService;

@RestController
@RequestMapping("/api/shopping/category")
public class ShoppingCategoryController {

  @Autowired
  ShoppingCategoryService service;

  @GetMapping("/{id}")
  public ResponseEntity<ShoppingCategoryDTO> getShoppingCategory(@PathVariable Long id) {
    ShoppingCategoryDTO shoppingCategoryDTO = service.getShoppingCategoryById(id);
    if (shoppingCategoryDTO != null) {
      return ResponseEntity.ok(shoppingCategoryDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<ShoppingCategoryDTO> saveShoppingCategory(@RequestBody ShoppingCategoryDTO category) {
    Long id = category.getId();
    ShoppingCategoryDTO shoppingCategoryDTO;
    if (id != null && id != 0) {
      shoppingCategoryDTO = service.updateShoppingCategory(category);
    } else {
      shoppingCategoryDTO = service.saveShoppingCategory(category);
    }
    if (shoppingCategoryDTO != null) {
      return ResponseEntity.ok(shoppingCategoryDTO);
    }
    return ResponseEntity.badRequest().build();
  }

}

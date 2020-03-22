package com.a29340.shoppinglist.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.dto.ShoppingListDTO;

@SpringBootTest
class ShoppingListServiceTest {

  @Autowired
  ShoppingListService service;

  @Test
  @Transactional
  void should_save_new_shopping_list() {

    ShoppingListDTO listDTO = new ShoppingListDTO();
    listDTO.setName("TestList");
    listDTO.setDescription("TestList description");
    ShoppingCategoryDTO shoppingCategoryDTO = new ShoppingCategoryDTO();
    shoppingCategoryDTO.setName("TestCategory");
    shoppingCategoryDTO.setDescription("TestCategory description");
    ShoppingElementDTO shoppingElementDTO = new ShoppingElementDTO();
    shoppingElementDTO.setName("TestElement");
    shoppingElementDTO.setDescription("TestElement description");
    shoppingElementDTO.setQuantity(5);
    shoppingCategoryDTO.setElementList(Arrays.asList(shoppingElementDTO));
    listDTO.setCategoryList(Arrays.asList(shoppingCategoryDTO));
    service.saveShoppingList(listDTO);

  }

  @Test
  @Transactional
  void should_save_new_shopping_list_with_null_values() {
    ShoppingListDTO listDTO = new ShoppingListDTO();
    listDTO.setName("TestList");
    listDTO.setDescription("TestList description");
    ShoppingCategoryDTO shoppingCategoryDTO = new ShoppingCategoryDTO();
    shoppingCategoryDTO.setName("TestCategory");
    ShoppingElementDTO shoppingElementDTO = new ShoppingElementDTO();
    shoppingElementDTO.setName("TestElement");
    shoppingElementDTO.setQuantity(5);
    shoppingCategoryDTO.setElementList(Arrays.asList(shoppingElementDTO));
    listDTO.setCategoryList(Arrays.asList(shoppingCategoryDTO));
    service.saveShoppingList(listDTO);
  }
}
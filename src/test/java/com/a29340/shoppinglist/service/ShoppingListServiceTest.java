package com.a29340.shoppinglist.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.dto.ShoppingListDTO;

@SpringBootTest
class ShoppingListServiceTest {

  @Autowired
  ShoppingListService service;

  @Test
  @Transactional
  void should_save_new_shopping_list() throws IOException {

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
    shoppingCategoryDTO.setElementList(Collections.singletonList(shoppingElementDTO));
    listDTO.setCategoryList(Collections.singletonList(shoppingCategoryDTO));
    service.saveShoppingList(listDTO, null);
  }

  @Test
  @Transactional
  void should_save_new_shopping_list_with_null_values() throws IOException {
    ShoppingListDTO listDTO = new ShoppingListDTO();
    listDTO.setName("TestList");
    listDTO.setDescription("TestList description");
    ShoppingCategoryDTO shoppingCategoryDTO = new ShoppingCategoryDTO();
    shoppingCategoryDTO.setName("TestCategory");
    ShoppingElementDTO shoppingElementDTO = new ShoppingElementDTO();
    shoppingElementDTO.setName("TestElement");
    shoppingElementDTO.setQuantity(5);
    shoppingCategoryDTO.setElementList(Collections.singletonList(shoppingElementDTO));
    listDTO.setCategoryList(Collections.singletonList(shoppingCategoryDTO));
    service.saveShoppingList(listDTO, null);
  }

  @Test
  @Transactional
  void should_save_two_shopping_list_with_shared_items() throws IOException {

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
    shoppingCategoryDTO.setElementList(Collections.singletonList(shoppingElementDTO));
    listDTO.setCategoryList(Collections.singletonList(shoppingCategoryDTO));
    service.saveShoppingList(listDTO, null);

    ShoppingListDTO anotherListDTO = new ShoppingListDTO();
    anotherListDTO.setName("TestList2");
    anotherListDTO.setCategoryList(Collections.singletonList(shoppingCategoryDTO));
    service.saveShoppingList(anotherListDTO, null);
  }

  @Test
  @Transactional
  void should_delete_list() throws IOException {
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
    shoppingCategoryDTO.setElementList(Collections.singletonList(shoppingElementDTO));
    listDTO.setCategoryList(Collections.singletonList(shoppingCategoryDTO));
    ShoppingListDTO savedDTO = service.saveShoppingList(listDTO, null);
    service.deleteShoppingList(savedDTO.getId());
  }


}

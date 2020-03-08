package com.a29340.shoppinglist.service;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.ShoppingList;
import com.a29340.shoppinglist.repository.ShoppingRepository;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingListServiceTest {

  @Autowired
  ShoppingListService service;

  @Test
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
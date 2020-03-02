package com.a29340.shoppinglist.service;

import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.ShoppingList;
import com.a29340.shoppinglist.repository.ShoppingListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

  @Autowired
  ShoppingListRepository slRepository;

  public ShoppingListDTO createShoppingList(ShoppingListDTO shoppingListDTO){
    ShoppingList sl = new ShoppingList();
    sl.setName(shoppingListDTO.getName());
    sl.setDescription(shoppingListDTO.getDescription());
    return ShoppingListDTO.fromShoppingList(slRepository.save(sl));
  }

  public Collection<ShoppingListDTO> getAllShoppingLists() {
    return slRepository.findAll().stream().map(ShoppingListDTO::fromShoppingList)
        .collect(Collectors.toList());
  }
}

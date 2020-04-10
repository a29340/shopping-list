package com.a29340.shoppinglist.service;

import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.model.ShoppingElement;
import com.a29340.shoppinglist.repository.ShoppingElementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingElementService {

  @Autowired
  private ShoppingElementRepository elementRepository;


  public ShoppingElementDTO getShoppingElementById(Long id) {
    Optional<ShoppingElement> shoppingElementOptional = elementRepository.findById(id);
    return shoppingElementOptional.map(ShoppingElementDTO::fromShoppingElement).orElse(null);
  }
}

package com.a29340.shoppinglist.controllers;

import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.ShoppingList;
import com.a29340.shoppinglist.model.User;
import com.a29340.shoppinglist.repository.UserRepository;
import com.a29340.shoppinglist.service.ShoppingListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shopping/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ShoppingListService listService;

  @GetMapping
  public Collection<ShoppingListDTO> getAllShoppingLists(Principal principal) {
    List<ShoppingListDTO> result = new ArrayList<>();
    Optional<User> optionalUser = userRepository.findByName(principal.getName());
    User user;
    if (!optionalUser.isPresent()) {
      user = new User();
      user.setName(principal.getName());
      userRepository.save(user);
    } else {
      user = optionalUser.get();
    }
    Set<ShoppingList> shoppingLists = user.getShoppingLists();
    if(shoppingLists != null) {
      List<ShoppingListDTO> listDTOS = shoppingLists.stream()
          .map(list -> listService.fromShoppingList(list))
          .collect(Collectors.toList());
      result.addAll(listDTOS);
    }
    return result;
  }
}

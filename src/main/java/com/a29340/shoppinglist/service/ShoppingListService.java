package com.a29340.shoppinglist.service;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.ShoppingCategory;
import com.a29340.shoppinglist.model.ShoppingElement;
import com.a29340.shoppinglist.model.ShoppingList;
import com.a29340.shoppinglist.repository.ShoppingCategoryRepository;
import com.a29340.shoppinglist.repository.ShoppingElementRepository;
import com.a29340.shoppinglist.repository.ShoppingListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

  @Autowired
  ShoppingListRepository listRepository;

  @Autowired
  ShoppingCategoryRepository categoryRepository;

  @Autowired
  ShoppingElementRepository elementRepository;

  public ShoppingListDTO saveShoppingList(ShoppingListDTO shoppingListDTO) {
    ShoppingList sl = listRepository.findByName(shoppingListDTO.getName());
    if (sl != null) {
      sl.setDescription(shoppingListDTO.getDescription());
    } else {
      sl = new ShoppingList();
      sl.setName(shoppingListDTO.getName());
      sl.setDescription(shoppingListDTO.getDescription());
    }
    sl.setCategoryList(processCategoryList(shoppingListDTO.getCategoryList()));

    return ShoppingListDTO.fromShoppingList(listRepository.save(sl));
  }

  private List<ShoppingCategory> processCategoryList(List<ShoppingCategoryDTO> categoryListDTO) {
    if (categoryListDTO != null) {
      List<ShoppingCategory> shoppingCategories = new ArrayList<>();
      categoryListDTO.forEach(categoryDTO -> {
        ShoppingCategory category = categoryRepository.findByName(categoryDTO.getName());
        if (category == null) {
          category = new ShoppingCategory();
          category.setName(categoryDTO.getName());
        }
        category.setDescription(categoryDTO.getDescription());
        category.setSubcategoryList(processCategoryList(categoryDTO.getSubcategoryList()));
        category.setElementList(processElementList(categoryDTO.getElementList()));
        shoppingCategories.add(categoryRepository.save(category));
      });
      return shoppingCategories;
    }
    return null;
  }

  private List<ShoppingElement> processElementList(List<ShoppingElementDTO> elementList) {
    if (elementList != null) {
      List<ShoppingElement> shoppingElements = new ArrayList<>();
      elementList.forEach(elementDTO -> {
        ShoppingElement shoppingElement = elementRepository.findByName(elementDTO.getName());
        if (shoppingElement == null) {
          shoppingElement = new ShoppingElement();
          shoppingElement.setName(elementDTO.getName());
        }
        shoppingElement.setDescription(elementDTO.getDescription());
        shoppingElement.setQuantity(elementDTO.getQuantity());
        shoppingElements.add(elementRepository.save(shoppingElement));
      });
      return shoppingElements;
    }
    return null;
  }

  public Collection<ShoppingListDTO> getAllShoppingLists() {
    return listRepository.findAll().stream().map(this::listWithCategory)
        .collect(Collectors.toList());
  }

  private ShoppingListDTO listWithCategory(ShoppingList shoppingList) {
    ShoppingListDTO slDTO = new ShoppingListDTO();
    slDTO.setId(shoppingList.getId());
    slDTO.setName(shoppingList.getName());
    slDTO.setDescription(shoppingList.getDescription());
    slDTO.setCategoryList(shoppingList.getCategoryList().stream().map( category -> {
      ShoppingCategoryDTO ctDTO = new ShoppingCategoryDTO();
      ctDTO.setId(category.getId());
      ctDTO.setDescription(category.getDescription());
      ctDTO.setName(category.getName());
      return ctDTO;
    }).collect(Collectors.toList()));
    return slDTO;
  }

  public Boolean deleteShoppingList(Long id) {
    Optional<ShoppingList> optionalShoppingList = listRepository.findById(id);
    if (optionalShoppingList.isPresent()) {
      listRepository.delete(optionalShoppingList.get());
      return true;
    }
    return false;
  }

  public ShoppingListDTO renameList(Long id, String newName) {
    Optional<ShoppingList> optionalShoppingList = listRepository.findById(id);
    if (optionalShoppingList.isPresent()) {
      ShoppingList shoppingList = optionalShoppingList.get();
      shoppingList.setName(newName);
      return listWithCategory(listRepository.save(shoppingList));
    }
    return null;
  }

  public ShoppingListDTO getShoppingListById(Long id) {
    Optional<ShoppingList> optionalShoppingList = listRepository.findById(id);
    ShoppingListDTO shoppingListDTO = null;
    if(optionalShoppingList.isPresent()){
      ShoppingList shoppingList = optionalShoppingList.get();
      shoppingListDTO = ShoppingListDTO.fromShoppingList(shoppingList);
    }
    return shoppingListDTO;
  }
}

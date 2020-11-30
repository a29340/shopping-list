package com.a29340.shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.dto.ShoppingListDTO;
import com.a29340.shoppinglist.model.ShoppingCategory;
import com.a29340.shoppinglist.model.ShoppingElement;
import com.a29340.shoppinglist.model.ShoppingList;
import com.a29340.shoppinglist.model.User;
import com.a29340.shoppinglist.repository.ShoppingCategoryRepository;
import com.a29340.shoppinglist.repository.ShoppingElementRepository;
import com.a29340.shoppinglist.repository.ShoppingListRepository;
import com.a29340.shoppinglist.repository.UserRepository;

@Service
public class ShoppingListService {

  @Autowired
  ShoppingListRepository listRepository;

  @Autowired
  ShoppingCategoryRepository categoryRepository;

  @Autowired
  ShoppingElementRepository elementRepository;

  @Autowired
  ShoppingCategoryService categoryService;

  public ShoppingListDTO saveShoppingList(ShoppingListDTO shoppingListDTO, User user) throws IOException {
    ShoppingList shoppingList;
    if (shoppingListDTO.getId() != null) {
      Optional<ShoppingList> sl = listRepository.findById(shoppingListDTO.getId());
      if (sl.isPresent()) {
        shoppingList = sl.get();
        shoppingList.setDescription(shoppingListDTO.getDescription());
      } else {
        throw new IOException(String.format("List not found in database with id: %s", shoppingListDTO.getId()));
      }
    } else {
      shoppingList = new ShoppingList();
    }
    shoppingList.setName(shoppingListDTO.getName());
    shoppingList.setDescription(shoppingListDTO.getDescription());
    List<ShoppingCategoryDTO> categoryList = shoppingListDTO.getCategoryList();
    if(categoryList!=null){
      shoppingList.setCategoryList(processCategoryList(categoryList));
    }
    user.getShoppingLists().add(shoppingList);
    return fromShoppingList(listRepository.save(shoppingList));
  }

  private List<ShoppingCategory> processCategoryList(List<ShoppingCategoryDTO> categoryListDTO) throws IOException {
    List<ShoppingCategory> shoppingCategories = new ArrayList<>();
    for (ShoppingCategoryDTO categoryDTO : categoryListDTO) {
      ShoppingCategory category;
      if (categoryDTO.getId() != null) {
        Optional<ShoppingCategory> categoryOptional = categoryRepository.findById(categoryDTO.getId());
        if (categoryOptional.isPresent()) {
          category = categoryOptional.get();
        } else {
          throw new IOException(String.format("Category not found in database with id: %s", categoryDTO.getId()));
        }
      } else {
        category = new ShoppingCategory();
      }
      category.setName(categoryDTO.getName());
      category.setDescription(categoryDTO.getDescription());
      List<ShoppingCategoryDTO> subcategoryList = categoryDTO.getSubcategoryList();
      if(subcategoryList!=null){
        category.setSubcategoryList(processCategoryList(subcategoryList));
      }
      List<ShoppingElementDTO> elementList = categoryDTO.getElementList();
      if (elementList != null) {
        category.setElementList(processElementList(elementList));
      }
      shoppingCategories.add(categoryRepository.save(category));
    }
    return shoppingCategories;
  }

  private List<ShoppingElement> processElementList(List<ShoppingElementDTO> elementList) throws IOException {
    List<ShoppingElement> shoppingElements = new ArrayList<>();
    for (ShoppingElementDTO elementDTO : elementList) {
      ShoppingElement shoppingElement;
      if (elementDTO.getId() != null) {
        Optional<ShoppingElement> optionalShoppingElement = elementRepository.findById(elementDTO.getId());
        if (optionalShoppingElement.isPresent()) {
          shoppingElement = optionalShoppingElement.get();
        } else {
          throw new IOException(String.format("Category not found in database with id: %s", elementDTO.getId()));
        }
      } else {
        shoppingElement = new ShoppingElement();
        shoppingElement.setName(elementDTO.getName());
      }
      shoppingElement.setDescription(elementDTO.getDescription());
      shoppingElement.setQuantity(elementDTO.getQuantity());
      shoppingElements.add(elementRepository.save(shoppingElement));
    }
    return shoppingElements;
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
    slDTO.setCategoryList(shoppingList.getCategoryList().stream().map(category -> {
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
    return optionalShoppingList.map(this::fromShoppingList).orElse(null);
  }

  public ShoppingListDTO fromShoppingList(ShoppingList sl) {
    ShoppingListDTO slDTO = new ShoppingListDTO();
    slDTO.setId(sl.getId());
    slDTO.setName(sl.getName());
    slDTO.setDescription(sl.getDescription());
    slDTO.setCategoryList(sl.getCategoryList() != null ? sl.getCategoryList().stream()
        .map(categoryService::fromShoppingCategory).collect(Collectors.toList()) : null);
    return slDTO;
  }
}

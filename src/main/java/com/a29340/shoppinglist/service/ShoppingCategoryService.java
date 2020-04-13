package com.a29340.shoppinglist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.model.ShoppingCategory;
import com.a29340.shoppinglist.model.ShoppingElement;
import com.a29340.shoppinglist.repository.ShoppingCategoryRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ShoppingCategoryService {

  @Autowired
  private ShoppingCategoryRepository categoryRepository;

  @Autowired
  private ShoppingElementService elementService;

  public ShoppingCategoryDTO getShoppingCategoryById(Long id) {
    Optional<ShoppingCategory> optionalShoppingCategory = categoryRepository.findById(id);
    return optionalShoppingCategory.map(this::fromShoppingCategory).orElse(null);
  }

  public ShoppingCategoryDTO updateShoppingCategory(ShoppingCategoryDTO category) {
    Optional<ShoppingCategory> categoryOptional = categoryRepository.findById(category.getId());
    if (categoryOptional.isPresent()) {
      ShoppingCategory sc = categoryOptional.get();
      sc.setName(category.getName());
      sc.setDescription(category.getDescription());
      sc.setElementList(saveOrUpdateShoppingElements(category.getElementList()));
      List<ShoppingCategory> shoppingCategoryList = category.getSubcategoryList().stream()
          .map(this::saveOrUpdateShoppingCategory)
          .map(this::fromShoppingCategoryDTO)
          .collect(Collectors.toList());
      sc.setSubcategoryList(shoppingCategoryList);
      return fromShoppingCategory(categoryRepository.save(sc));
    }
    return null;
  }

  public ShoppingCategoryDTO saveShoppingCategory(ShoppingCategoryDTO category) {
    ShoppingCategory sc = new ShoppingCategory();
    sc.setName(category.getName());
    sc.setDescription(category.getDescription());
    sc.setElementList(saveOrUpdateShoppingElements(category.getElementList()));
    List<ShoppingCategory> shoppingCategoryList = category.getSubcategoryList().stream()
        .map(this::saveOrUpdateShoppingCategory)
        .map(this::fromShoppingCategoryDTO)
        .collect(Collectors.toList());
    sc.setSubcategoryList(shoppingCategoryList);
    return fromShoppingCategory(categoryRepository.save(sc));
  }

  private List<ShoppingElement> saveOrUpdateShoppingElements(Collection<ShoppingElementDTO> elementList) {
    return elementList.stream()
        .map(elementService::updateOrSaveElement)
        .map(elementService::fromShoppingElementDTO)
        .collect(Collectors.toList());
  }

  public ShoppingCategoryDTO saveOrUpdateShoppingCategory(ShoppingCategoryDTO categoryDTO) {
    Long id = categoryDTO.getId();
    if(id != null && id != 0) {
      return updateShoppingCategory(categoryDTO);
    } else {
      return saveShoppingCategory(categoryDTO);
    }
  }

  public ShoppingCategory fromShoppingCategoryDTO(ShoppingCategoryDTO shoppingCategoryDTO) {
    ShoppingCategory shoppingCategory;
    Long id = shoppingCategoryDTO.getId();
    if(id!=null && id != 0){
      Optional<ShoppingCategory> optionalShoppingCategory = categoryRepository.findById(id);
      if(optionalShoppingCategory.isPresent()){
        shoppingCategory = optionalShoppingCategory.get();
      } else {
        return null;
      }
    } else {
      shoppingCategory = new ShoppingCategory();
    }
    shoppingCategory.setName(shoppingCategoryDTO.getName());
    shoppingCategory.setDescription(shoppingCategoryDTO.getDescription());
    shoppingCategory.setName(shoppingCategoryDTO.getName());
    List<ShoppingElement> shoppingElementList = shoppingCategoryDTO.getElementList().stream()
        .map(elementService::fromShoppingElementDTO)
        .collect(Collectors.toList());
    shoppingCategory.setElementList(shoppingElementList);
    List<ShoppingCategory> shoppingCategoryList = shoppingCategoryDTO.getSubcategoryList().stream()
        .map(this::fromShoppingCategoryDTO)
        .collect(Collectors.toList());
    shoppingCategory.setSubcategoryList(shoppingCategoryList);
    return shoppingCategory;
  }

  public ShoppingCategoryDTO fromShoppingCategory(ShoppingCategory sc) {
    ShoppingCategoryDTO scDTO = new ShoppingCategoryDTO();
    scDTO.setId(sc.getId());
    scDTO.setName(sc.getName());
    scDTO.setDescription(sc.getDescription());
    scDTO.setElementList(sc.getElementList() != null ? sc.getElementList().stream()
        .map(elementService::fromShoppingElement).collect(Collectors.toList()) : null);
    scDTO.setSubcategoryList(sc.getSubcategoryList() != null ? sc.getSubcategoryList().stream()
        .map(this::fromShoppingCategory).collect(Collectors.toList()) : null);
    return scDTO;
  }

}

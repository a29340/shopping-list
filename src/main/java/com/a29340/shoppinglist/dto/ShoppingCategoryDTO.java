package com.a29340.shoppinglist.dto;

import com.a29340.shoppinglist.model.ShoppingCategory;
import com.a29340.shoppinglist.model.ShoppingElement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCategoryDTO {

  private Long id;

  private List<ShoppingElementDTO> elementList;

  private List<ShoppingCategoryDTO> subcategoryList;

  private String name;

  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<ShoppingElementDTO> getElementList() {
    return elementList;
  }

  public void setElementList(List<ShoppingElementDTO> elementList) {
    this.elementList = elementList;
  }

  public List<ShoppingCategoryDTO> getSubcategoryList() {
    return subcategoryList;
  }

  public void setSubcategoryList(List<ShoppingCategoryDTO> subcategoryList) {
    this.subcategoryList = subcategoryList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static ShoppingCategoryDTO fromShoppingCategory(ShoppingCategory sc) {
    ShoppingCategoryDTO scDTO = new ShoppingCategoryDTO();
    scDTO.setId(sc.getId());
    scDTO.setName(sc.getName());
    scDTO.setDescription(sc.getDescription());
    scDTO.setElementList(sc.getElementList() != null ? sc.getElementList().stream()
            .map(ShoppingElementDTO::fromShoppingElement).collect(Collectors.toList()) : null);
    scDTO.setSubcategoryList(sc.getSubcategoryList() != null ? sc.getSubcategoryList().stream()
            .map(ShoppingCategoryDTO::fromShoppingCategory).collect(Collectors.toList()) : null);
    return scDTO;
  }

}

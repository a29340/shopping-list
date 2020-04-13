package com.a29340.shoppinglist.dto;

import java.util.List;

public class ShoppingCategoryDTO {

  private Long id;

  private List<ShoppingElementDTO> elementList;

  private List<ShoppingCategoryDTO> subcategoryList;

  private String name;

  private String description;

  public ShoppingCategoryDTO() {
  }

  public ShoppingCategoryDTO(Long id, List<ShoppingElementDTO> elementList, List<ShoppingCategoryDTO> subcategoryList, String name, String description) {
    this.id = id;
    this.elementList = elementList;
    this.subcategoryList = subcategoryList;
    this.name = name;
    this.description = description;
  }

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




}

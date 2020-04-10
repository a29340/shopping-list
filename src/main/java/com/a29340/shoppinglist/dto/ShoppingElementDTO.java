package com.a29340.shoppinglist.dto;

import com.a29340.shoppinglist.model.ShoppingElement;

import javax.persistence.Entity;
import javax.persistence.Id;

public class ShoppingElementDTO {

  private Long id;

  private String name;
  private String description;
  private Integer quantity;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public static ShoppingElementDTO fromShoppingElement(ShoppingElement se) {
    ShoppingElementDTO seDTO = new ShoppingElementDTO();
    seDTO.setId(se.getId());
    seDTO.setName(se.getName());
    seDTO.setDescription(se.getDescription());
    seDTO.setQuantity(se.getQuantity());
    return seDTO;
  }
}

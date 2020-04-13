package com.a29340.shoppinglist.dto;

import com.a29340.shoppinglist.model.ShoppingElement;

import javax.persistence.Entity;
import javax.persistence.Id;

public class ShoppingElementDTO {

  private Long id;

  private String name;
  private String description;
  private Integer quantity;
  private Boolean isChecked;

  public ShoppingElementDTO() {
  }

  public ShoppingElementDTO(Long id, String name, String description, Integer quantity, Boolean isChecked) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.isChecked = isChecked;
  }

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

  public Boolean getChecked() {
    return isChecked;
  }

  public void setChecked(Boolean checked) {
    isChecked = checked;
  }






}

package com.a29340.shoppinglist.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShoppingElement {

  @Id
  private String id;

  private String name;
  private String description;
  private Integer quantity;

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
}

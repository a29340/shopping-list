package com.a29340.shoppinglist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShoppingElement {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String description;

  private Integer quantity;

  private Boolean isChecked;

  public Long getId() {
    return id;
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

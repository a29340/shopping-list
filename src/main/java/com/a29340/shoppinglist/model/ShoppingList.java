package com.a29340.shoppinglist.model;


import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class ShoppingList {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  private List<ShoppingCategory> categoryList;

  @Column(unique = true)
  private String name;

  private String description;
  public List<ShoppingCategory> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<ShoppingCategory> categoryList) {
    this.categoryList = categoryList;
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

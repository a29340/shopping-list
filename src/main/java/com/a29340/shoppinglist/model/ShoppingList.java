package com.a29340.shoppinglist.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShoppingList {

  @Id
  private String id;

  @OneToMany
  private List<ShoppingCategory> categoryList;

  public List<ShoppingCategory> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<ShoppingCategory> categoryList) {
    this.categoryList = categoryList;
  }


}

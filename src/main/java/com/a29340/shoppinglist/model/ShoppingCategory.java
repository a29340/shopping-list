package com.a29340.shoppinglist.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCategory {

  @Id
  private String id;

  @OneToMany
  private List<ShoppingElement> elementList;


  public List<ShoppingElement> getElementList() {
    return elementList;
  }

  public void setElementList(List<ShoppingElement> elementList) {
    this.elementList = elementList;
  }

  public List<ShoppingCategory> getSubcategoryList() {
    return subcategoryList;
  }

  public void setSubcategoryList(List<ShoppingCategory> subcategoryList) {
    this.subcategoryList = subcategoryList;
  }

  @OneToMany
  private List<ShoppingCategory> subcategoryList;

}

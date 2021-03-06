package com.a29340.shoppinglist.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCategory {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String description;

  @OneToMany(cascade = CascadeType.REMOVE)
  private List<ShoppingElement> elementList;


  @OneToMany(cascade = CascadeType.REMOVE)
  private List<ShoppingCategory> subcategoryList;

  public Long getId() {
    return id;
  }

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

package com.a29340.shoppinglist.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ShoppingList {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(cascade = CascadeType.REMOVE)
  private List<ShoppingCategory> categoryList;

  private String name;

  private String description;

  @ManyToMany(mappedBy = "shoppingLists")
  private Set<User> users;

  public ShoppingList() {
    setUsers(new HashSet<>());
  }

  public List<ShoppingCategory> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<ShoppingCategory> categoryList) {
    this.categoryList = categoryList;
  }

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


  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}

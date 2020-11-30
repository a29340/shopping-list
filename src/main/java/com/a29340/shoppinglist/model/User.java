package com.a29340.shoppinglist.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany
  private Set<ShoppingList> shoppingLists;

  public User() {
  }

  public User(String name) {
    setName(name);
    setShoppingLists(new HashSet<>());
  }

  public Set<ShoppingList> getShoppingLists() {
    return shoppingLists;
  }

  public void setShoppingLists(Set<ShoppingList> shoppingLists) {
    this.shoppingLists = shoppingLists;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}

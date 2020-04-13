package com.a29340.shoppinglist.dto;

import com.a29340.shoppinglist.model.ShoppingList;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingListDTO {
    private List<ShoppingCategoryDTO> categoryList;

    private Long id;

    private String name;

    private String description;

    public ShoppingListDTO() {
    }

    public ShoppingListDTO(List<ShoppingCategoryDTO> categoryList, Long id, String name, String description) {
        this.categoryList = categoryList;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public List<ShoppingCategoryDTO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ShoppingCategoryDTO> categoryList) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

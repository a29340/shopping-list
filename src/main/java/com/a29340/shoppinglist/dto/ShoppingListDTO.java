package com.a29340.shoppinglist.dto;

import com.a29340.shoppinglist.model.ShoppingList;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingListDTO {
    private List<ShoppingCategoryDTO> categoryList;

    private String name;

    private String description;

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

    public static ShoppingListDTO fromShoppingList(ShoppingList sl){
        ShoppingListDTO slDTO = new ShoppingListDTO();
        slDTO.setName(sl.getName());
        slDTO.setDescription(sl.getDescription());
        slDTO.setCategoryList(sl.getCategoryList() != null ? sl.getCategoryList().stream()
                .map(ShoppingCategoryDTO::fromShoppingCategory).collect(Collectors.toList()) : null);
        return slDTO;
    }
}

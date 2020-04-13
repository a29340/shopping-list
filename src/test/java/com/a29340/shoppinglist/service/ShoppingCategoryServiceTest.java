package com.a29340.shoppinglist.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.a29340.shoppinglist.dto.ShoppingCategoryDTO;
import com.a29340.shoppinglist.dto.ShoppingElementDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ShoppingCategoryServiceTest {

  @Autowired
  ShoppingCategoryService service;

  @Test
  @Transactional
  void should_save_new_shopping_category() {
    service.saveShoppingCategory(getShoppingCategoryWithSuffix(" 1"));
  }

  private ShoppingCategoryDTO getShoppingCategoryWithSuffix(String suffix) {
    List<ShoppingElementDTO> shoppingElementDTOList = Arrays.asList(
        new ShoppingElementDTO(null, "carciofo" + suffix, "verde" + suffix, 2, true),
        new ShoppingElementDTO(null, "patate" + suffix, "gialle" + suffix, 3, false)
    );
    return new ShoppingCategoryDTO(null, shoppingElementDTOList, new ArrayList<>(), "Verdura" + suffix, "quella buona" + suffix);
  }

}

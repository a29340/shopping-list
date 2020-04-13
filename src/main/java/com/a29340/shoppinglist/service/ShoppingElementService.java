package com.a29340.shoppinglist.service;

import com.a29340.shoppinglist.dto.ShoppingElementDTO;
import com.a29340.shoppinglist.model.ShoppingElement;
import com.a29340.shoppinglist.repository.ShoppingElementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ShoppingElementService {

  @Autowired
  private ShoppingElementRepository elementRepository;


  public ShoppingElementDTO getShoppingElementById(Long id) {
    Optional<ShoppingElement> shoppingElementOptional = elementRepository.findById(id);
    return shoppingElementOptional.map(this::fromShoppingElement).orElse(null);
  }

  private ShoppingElementDTO saveShoppingElement(ShoppingElementDTO element) {
    ShoppingElement shoppingElement = fromShoppingElementDTO(element);
    return fromShoppingElement(elementRepository.save(shoppingElement));
  }

  private ShoppingElementDTO updateShoppingElement(ShoppingElementDTO element) {
    Optional<ShoppingElement> shoppingElementOptional = elementRepository.findById(element.getId());
    if (shoppingElementOptional.isPresent()) {
      ShoppingElement se = shoppingElementOptional.get();
      se.setChecked(element.getChecked());
      se.setName(element.getName());
      se.setDescription(element.getDescription());
      se.setQuantity(element.getQuantity());
      return fromShoppingElement(elementRepository.save(se));
    } else {
      return null;
    }
  }

  public ShoppingElementDTO updateOrSaveElement(ShoppingElementDTO element) {
    Long id = element.getId();
    ShoppingElementDTO shoppingElementDTO;
    if (id != null && id != 0) {
      shoppingElementDTO = updateShoppingElement(element);
    } else {
      shoppingElementDTO = saveShoppingElement(element);
    }
    return shoppingElementDTO;
  }

  public ShoppingElement fromShoppingElementDTO(ShoppingElementDTO seDTO) {
    ShoppingElement se;
    Long id = seDTO.getId();
    if (id != null && id != 0) {
      Optional<ShoppingElement> shoppingElementOptional = elementRepository.findById(id);
      if (shoppingElementOptional.isPresent()) {
        se = shoppingElementOptional.get();
      } else {
        return null;
      }
    } else {
      se = new ShoppingElement();
    }
    se.setName(seDTO.getName());
    se.setDescription(seDTO.getDescription());
    se.setQuantity(seDTO.getQuantity());
    se.setChecked(seDTO.getChecked() != null ? seDTO.getChecked() : false);
    return se;
  }

  public ShoppingElementDTO fromShoppingElement(ShoppingElement se) {
    ShoppingElementDTO seDTO = new ShoppingElementDTO();
    seDTO.setId(se.getId());
    seDTO.setName(se.getName());
    seDTO.setDescription(se.getDescription());
    seDTO.setQuantity(se.getQuantity());
    seDTO.setChecked(se.getChecked()!=null? se.getChecked() : false);
    return seDTO;
  }


}

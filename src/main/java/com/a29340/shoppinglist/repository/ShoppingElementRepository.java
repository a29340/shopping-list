package com.a29340.shoppinglist.repository;


import com.a29340.shoppinglist.model.ShoppingCategory;
import com.a29340.shoppinglist.model.ShoppingElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingElementRepository extends JpaRepository<ShoppingElement, Long> {


}

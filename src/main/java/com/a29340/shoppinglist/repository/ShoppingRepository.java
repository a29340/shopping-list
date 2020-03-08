package com.a29340.shoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ShoppingRepository<T> extends JpaRepository<T, Long> {
    T findByName(String name);
}

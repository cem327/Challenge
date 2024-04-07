package org.cem.repository;

import org.cem.entity.Product_Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface Product_CartRepository extends JpaRepository<Product_Cart, Long> {
    List<Product_Cart> findAllByCartId(Long id);
}

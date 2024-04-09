package org.cem.repository;

import org.cem.entity.Product_Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Product_CartRepository extends JpaRepository<Product_Cart, Long> {
    Optional<Product_Cart> findOptionalByCartIdAndProductId(Long cartId, Long productId);

    List<Product_Cart> findAllByCartId(Long cartId);
}

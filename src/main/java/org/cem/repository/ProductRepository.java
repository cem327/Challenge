package org.cem.repository;

import org.cem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}

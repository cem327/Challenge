package org.cem.repository;

import org.cem.entity.Cart;
import org.cem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long id);
}

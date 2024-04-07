package org.cem.service;

import org.cem.entity.Cart_Order;
import org.cem.repository.Cart_OrderRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class Cart_OrderService extends ServiceManager<Cart_Order, Long> {
    private final Cart_OrderRepository cart_OrderRepository;

    public Cart_OrderService(Cart_OrderRepository cart_OrderRepository) {
        super(cart_OrderRepository);
        this.cart_OrderRepository = cart_OrderRepository;
    }
}

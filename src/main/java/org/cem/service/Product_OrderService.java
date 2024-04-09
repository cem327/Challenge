package org.cem.service;

import org.cem.entity.Product_Order;
import org.cem.repository.Product_OrderRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class Product_OrderService extends ServiceManager<Product_Order, Long> {
    private final Product_OrderRepository product_OrderRepository;

    public Product_OrderService(Product_OrderRepository product_OrderRepository) {
        super(product_OrderRepository);
        this.product_OrderRepository = product_OrderRepository;
    }
}

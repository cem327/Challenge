package org.cem.service;

import org.cem.entity.Product_Cart;
import org.cem.repository.Product_CartRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Product_CartService extends ServiceManager<Product_Cart, Long> {

    private final Product_CartRepository productCartRepository;

    public Product_CartService(Product_CartRepository productCartRepository) {
        super(productCartRepository);
        this.productCartRepository = productCartRepository;

    }

    public Optional<Product_Cart> findOptionalByCartIdAndProductId(Long cartId, Long productId) {
        return productCartRepository.findOptionalByCartIdAndProductId(cartId, productId);
    }

    public List<Product_Cart> findAllByCartId(Long cartId) {
        return productCartRepository.findAllByCartId(cartId);
    }
}

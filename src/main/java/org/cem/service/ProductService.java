package org.cem.service;

import org.cem.dto.request.ProductCreateProductRequestDto;
import org.cem.entity.Order;
import org.cem.entity.Product;
import org.cem.repository.ProductRepository;
import org.cem.repository.Product_CartRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceManager<Product, Long> {

    private final ProductRepository productRepository;
    private final Product_CartService productCartService;

    public ProductService(ProductRepository productRepository, Product_CartService productCartService) {
        super(productRepository);
        this.productRepository = productRepository;
        this.productCartService = productCartService;
    }

    public Product getProduct(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }


    public Product createProduct(ProductCreateProductRequestDto dto) {

        return productRepository.save(Product.builder()
                        .name(dto.getName())
                        .price(dto.getPrice())
                        .currentStock(dto.getCurrentStock())
                        .build());
    }
}

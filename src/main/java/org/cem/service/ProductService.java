package org.cem.service;

import jakarta.persistence.EntityNotFoundException;
import org.cem.dto.request.ProductCreateProductRequestDto;
import org.cem.entity.Product;
import org.cem.repository.ProductRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceManager<Product, Long> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
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

    public Product findProductByIdElseThrowException(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }


}

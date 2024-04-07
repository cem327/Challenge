package org.cem.controller;

import lombok.RequiredArgsConstructor;
import org.cem.dto.request.ProductCreateProductRequestDto;
import org.cem.entity.Product;
import org.cem.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.cem.constants.RestApiUrls.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping(CREATE_PRODUCT)
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateProductRequestDto dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }
    @GetMapping(GET_PRODUCT)
    public ResponseEntity<Product> getProduct(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @PutMapping(UPDATE_PRODUCT)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }
    @DeleteMapping(DELETE_PRODUCT)
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

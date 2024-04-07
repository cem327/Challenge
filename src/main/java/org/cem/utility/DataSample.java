package org.cem.utility;

import jakarta.annotation.PostConstruct;
import org.cem.dto.request.CustomerAddCustomerRequestDto;
import org.cem.entity.Product;
import org.cem.service.CartService;
import org.cem.service.CustomerService;
import org.cem.service.OrderService;
import org.cem.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class DataSample {

    private final CustomerService customerService;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;

    public DataSample(CustomerService customerService, CartService cartService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.productService = productService;
        this.orderService = orderService;
    }
    @PostConstruct
    public void init() {
        CustomerAddCustomerRequestDto dto = CustomerAddCustomerRequestDto.builder()
                .name("John")
                .email("johndoe")
                .password("password123")
                .build();
        CustomerAddCustomerRequestDto dto2 = CustomerAddCustomerRequestDto.builder()
                .name("Jane")
                .email("janedoe")
                .password("password123")
                .build();
        Product product =  Product.builder()
                .name("Seker")
                .price(15.0)
                .currentStock(3400)
                .build();

        customerService.addCustomer(dto);
        customerService.addCustomer(dto2);
        productService.save(product);
        cartService.addProductToCart(1L, 1L, 10);
        

    }
}

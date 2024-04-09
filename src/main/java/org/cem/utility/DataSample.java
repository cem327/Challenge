package org.cem.utility;

import lombok.extern.slf4j.Slf4j;
import org.cem.dto.request.CustomerAddCustomerRequestDto;
import org.cem.entity.Customer;
import org.cem.entity.Order;
import org.cem.entity.Product;
import org.cem.service.CartService;
import org.cem.service.CustomerService;
import org.cem.service.OrderService;
import org.cem.service.ProductService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataSample {

    private final CustomerService customerService;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;


    public DataSample(CustomerService customerService,
                      CartService cartService,
                      ProductService productService,
                      OrderService orderService) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.productService = productService;
        this.orderService = orderService;

    }

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
        Product product = Product.builder()
                .name("Seker")
                .price(15.0)
                .currentStock(3400)
                .build();
        Product product1 = Product.builder()
                .name("Un")
                .price(30.0)
                .currentStock(1500)
                .build();
        Product product2 = Product.builder()
                .name("Biber")
                .price(12.0)
                .currentStock(1000)
                .build();

        Customer customer1 = customerService.addCustomer(dto);
        Customer customer2 = customerService.addCustomer(dto2);

        log.info("Customer1 ---> " + customer1 + "\n");
        log.info("Customer2 ---> " + customer2 + "\n");

        Product savedProduct = productService.save(product);
        Product savedProduct1 = productService.save(product1);
        Product savedProduct2 = productService.save(product2);
        log.info("Product1 ---> " + savedProduct + "\n");
        log.info("Product2 ---> " + savedProduct1 + "\n");
        log.info("Product3 ---> " + savedProduct2 + "\n");
        try {
            cartService.addProductToCart(savedProduct.getId(), customer1.getCartId(), 5);
            log.info("Customer 1 Cart1 ---> " + cartService.getCart(customer1.getCartId()) + "\n");

            cartService.addProductToCart(savedProduct1.getId(), customer2.getCartId(), 15);
            log.info("Customer 2 Cart2 ---> " + cartService.getCart(customer1.getCartId()) + "\n");
            cartService.addProductToCart(savedProduct2.getId(), customer2.getCartId(), 35);
            log.info("Customer 2 Product Id 1 added to Cart2 ---> " + cartService.getCart(customer2.getCartId()) + "\n");

            Long cartId1 = customer1.getCartId();
            Long cartId2 = customer2.getCartId();
            Order order = orderService.startOrderProcess(cartId1);
            log.info("Order1 ---> " + order + "\n");
            log.info("Customer1 after order has been placed. ----> " + customerService.findById(customer1.getId()) + "\n");

            Order order1 = orderService.startOrderProcess(cartId2);
            log.info("Order2 ---> " + order1 + "\n");
            log.info("Customer2 after order has been placed. ----> " + customerService.findById(customer2.getId()) + "\n");


            orderService.placeOrder(order.getId());
            orderService.placeOrder(order1.getId());


            log.info("Product1 after order has been placed. ----> " + productService.getProduct(savedProduct.getId()) + "\n");
            log.info("Product2 after order has been placed. ----> " + productService.getProduct(savedProduct1.getId()) + "\n");
            log.info("Product2 after order has been placed. ----> " + productService.getProduct(savedProduct2.getId()) + "\n");
        } catch (Exception e) {
            log.error("Error--------> " + e.getMessage());
        }



    }
}

package org.cem.service;

import org.cem.dto.request.CustomerAddCustomerRequestDto;
import org.cem.entity.Cart;
import org.cem.entity.Customer;
import org.cem.repository.CustomerRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService extends ServiceManager<Customer, Long> {

    private final CustomerRepository customerRepository;
    private final CartService cartService;

    public CustomerService(CustomerRepository customerRepository, CartService cartService) {
        super(customerRepository);
        this.customerRepository = customerRepository;
        this.cartService = cartService;
    }

    public Customer addCustomer(CustomerAddCustomerRequestDto dto) {
        Cart cart = cartService.save(Cart.builder()
                .totalPrice(0.0)
                .productCartIds(new ArrayList<>())
                .build());

        Customer savedCustomer = save(Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .cartId(cart.getId())
                .orderIds(new ArrayList<>())
                .build());
        cart.setCustomerId(savedCustomer.getId());
        cartService.update(cart);
        return savedCustomer;
    }

    public Customer findByCartId(Long cartId) {
        return customerRepository.findByCartId(cartId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}

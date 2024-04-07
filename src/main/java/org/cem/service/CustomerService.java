package org.cem.service;

import lombok.RequiredArgsConstructor;
import org.cem.dto.request.CustomerAddCustomerRequestDto;
import org.cem.entity.Cart;
import org.cem.entity.Customer;
import org.cem.repository.CustomerRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

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
        return save(Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .cart(cartService.save(new Cart()))
                .build());

    }
}

package org.cem.controller;

import lombok.RequiredArgsConstructor;
import org.cem.dto.request.CustomerAddCustomerRequestDto;
import org.cem.entity.Customer;
import org.cem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.cem.constants.RestApiUrls.ADD_CUSTOMER;
import static org.cem.constants.RestApiUrls.CUSTOMER;

@RestController
@RequestMapping(CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(ADD_CUSTOMER)
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerAddCustomerRequestDto dto) {
        return ResponseEntity.ok(customerService.addCustomer(dto));
    }


}

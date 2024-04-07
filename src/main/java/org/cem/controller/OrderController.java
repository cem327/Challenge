package org.cem.controller;

import lombok.RequiredArgsConstructor;
import org.cem.entity.Cart;
import org.cem.entity.Order;
import org.cem.service.CustomerService;
import org.cem.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.cem.constants.RestApiUrls.*;

@RestController
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(PLACE_ORDER)
    public ResponseEntity<Void> placeOrder(@RequestBody Long orderId ) {
        orderService.placeOrder(orderId);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GET_ORDER_FOR_CODE)
    public ResponseEntity<Order> getOrderForCode(@RequestParam Long id) {
        return ResponseEntity.ok(orderService.getOrderForCode(id));
    }
    @GetMapping(GET_ALL_ORDERS_FOR_CUSTOMER)
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@RequestParam Long id) {
        return ResponseEntity.ok(orderService.getAllOrdersForCustomer(id));
    }
    @PostMapping(START_ORDER_PROCESS)
    public ResponseEntity<Order> startOrderProcess(@RequestParam Long cartId) {
        return ResponseEntity.ok(orderService.startOrderProcess(cartId));
    }

}

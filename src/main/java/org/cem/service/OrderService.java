package org.cem.service;

import lombok.extern.slf4j.Slf4j;
import org.cem.entity.*;
import org.cem.repository.OrderRepository;
import org.cem.utility.ServiceManager;
import org.cem.utility.enums.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService extends ServiceManager<Order, Long> {

    private final OrderRepository orderRepository;
    private final Product_OrderService product_orderService;
    private final CartService cartService;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository,
                        Product_OrderService product_orderService,
                        CartService cartService,
                        ProductService productService,
                        CustomerService customerService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.product_orderService = product_orderService;
        this.cartService = cartService;
        this.productService = productService;
        this.customerService = customerService;
    }

    /**
     * Method to go into order phase. The order will be set to "pending" till, it is placed.
     * This method will be used to hold order till credentials of the user are verified.(Credit card, address etc.)
     *
     * @param cartId - cart id
     * @return - order
     */
    public Order startOrderProcess(Long cartId) {

        Cart cart = cartService.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        Double totalPrice = cartService.calculateTotalPrice(cartId);
        Customer customer = customerService.findByCartId(cartId);

        Order savedOrder = orderRepository.save(Order.builder()
                .totalPrice(totalPrice)
                .status(Status.PENDING)
                .customerId(customer.getId())
                .productOrderIds(new ArrayList<>())
                .build());

        cartService.getProductCartPairsByCartId(cartId).forEach(productCart -> {
            Product product = productService.findById(productCart.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            Product_Order cartOrder = product_orderService.save(Product_Order.builder()
                    .orderId(savedOrder.getId())
                    .productId(productCart.getProductId())
                    .quantity(productCart.getQuantity())
                    .price(product.getPrice())
                    .build());
            savedOrder.getProductOrderIds().add(cartOrder.getId());

            product.setCurrentStock(product.getCurrentStock() - productCart.getQuantity());
            productService.save(product);

        });

        // Customer will be updated with order.
        customer.getOrderIds().add(savedOrder.getId());
        customerService.update(customer);


        return orderRepository.save(savedOrder);
    }

    public Order placeOrder(Long orderId) {
        Order order = getOrderForCode(orderId);
        order.setStatus(Status.PLACED);
        order.setOrderDate(java.time.LocalDate.now());
        return orderRepository.save(order);
    }

    public Order getOrderForCode(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrdersForCustomer(Long id) {
        return orderRepository.findAllByCustomerId(id);
    }
}

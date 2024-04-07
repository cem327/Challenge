package org.cem.service;

import jakarta.transaction.Transactional;
import org.cem.entity.Cart;
import org.cem.entity.Product;
import org.cem.entity.Cart_Order;
import org.cem.entity.Order;
import org.cem.repository.OrderRepository;
import org.cem.utility.ServiceManager;
import org.cem.utility.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends ServiceManager<Order, Long> {

    private final OrderRepository orderRepository;
    private final Cart_OrderService cart_orderService;
    private final CartService cartService;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, Cart_OrderService cart_orderService, CartService cartService, ProductService productService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.cart_orderService = cart_orderService;
        this.cartService = cartService;
        this.productService = productService;
    }

    /**
     * Method to go into order phase. The order will be set to "pending" till, it is placed.
     * This method will be used to hold order till credentials of the user are verified.(Credit card, address etc.)
     *
     * @param cartId - cart id
     * @return - order
     */
    @Transactional
    public Order startOrderProcess(Long cartId) {
        Cart cart = cartService.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Double totalPrice = cartService.calculateTotalPrice(cartId);
        Order savedOrder = orderRepository.save(Order.builder()
                .totalPrice(totalPrice)
                .status(Status.PENDING)
                .customer(cart.getCustomer())
                .build());
        cartService.getProductCartPairsByCartId(cartId).forEach(productCart -> {
            cart_orderService.save(Cart_Order.builder()
                    .orderId(savedOrder.getId())
                    .productId(productCart.getProductId())
                    .quantity(productCart.getQuantity())
                    .build());

            Product product = productService.findById(productCart.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            product.setCurrentStock(product.getCurrentStock() - productCart.getQuantity());
            productService.save(product);

        });

        return savedOrder;
    }

    public void placeOrder(Long orderId) {
       Order order = getOrderForCode(orderId);
       order.setStatus(Status.PLACED);

       orderRepository.save(order);
    }

    public Order getOrderForCode(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrdersForCustomer(Long id) {
        return orderRepository.findAllByCustomerId(id);
    }
}

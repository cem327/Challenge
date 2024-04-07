package org.cem.controller;

import lombok.RequiredArgsConstructor;
import org.cem.entity.Cart;
import org.cem.service.CartService;
import org.cem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.cem.constants.RestApiUrls.*;

@RestController
@RequestMapping(CART)
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping(GET_CART)
    public ResponseEntity<Cart> getCart(@RequestParam Long id) {
        return ResponseEntity.ok(cartService.getCart(id));
    }
    @PutMapping(UPDATE_CART)
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.save(cart));
    }
    @DeleteMapping(EMPTY_CART)
    public ResponseEntity<Void> emptyCart(@RequestParam Long id) {
        cartService.emptyCart(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(ADD_PRODUCT_TO_CART)
    public ResponseEntity<Cart> addProductToCart(@RequestParam Long productId, Long cartId, Integer quantity) {
        return ResponseEntity.ok(cartService.addProductToCart(productId, cartId, quantity));

    }
}

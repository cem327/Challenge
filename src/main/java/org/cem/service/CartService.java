package org.cem.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.cem.entity.Cart;
import org.cem.entity.Product;
import org.cem.entity.Product_Cart;
import org.cem.repository.CartRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService extends ServiceManager<Cart, Long> {

    private final CartRepository cartRepository;
    private final Product_CartService productCartService;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, Product_CartService productCartService, ProductService productService) {
        super(cartRepository);
        this.cartRepository = cartRepository;
        this.productCartService = productCartService;
        this.productService = productService;
    }


    public Cart getCart(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void emptyCart(Long id) {
//        Cart cart = getCart(id);
//        cart.getProductList().clear();
//        save(cart);
    }

    @Transactional
    public Cart addProductToCart(Long productId, Long cartId, Integer quantity) {
        Product product =productService.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + cartId));
        if(cart.getProductCartIds().contains(productId)){
        // TODO
        }
        Product_Cart productCart = Product_Cart.builder()
                .cartId(cartId)
                .productId(productId)
                .quantity(quantity)
                .build();
        Product_Cart savedProduct_Cart = productCartService.save(productCart);

        cart.getProductCartIds().add(savedProduct_Cart.getId());
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice()*quantity);
        return cartRepository.save(cart);
    }

    /**
     * The purpose of this method is to return the list of products in the cart
     * If user moves to the order phase, the order will not be affected of change in the prices of the products.
     *
     * @param cartId - cartId
     * @return
     */
    @Transactional
    public List<Product> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + cartId));
        return  getProductCartPairsByCartId(cartId)
                .stream()
                .map(productCart -> productService.findById(productCart.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productCart.getProductId())))
                .collect(Collectors.toList());
    }

    public List<Product_Cart> getProductCartPairsByCartId(Long cartId){
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + cartId))
                .getProductCartIds()
                .stream()
                .map(productCartId -> productCartService.findById(productCartId)
                        .orElseThrow(() -> new EntityNotFoundException("ProductCart not found with id: " + productCartId)))
                .collect(Collectors.toList());
    }

    public Double calculateTotalPrice(Long cartId){
        return getProductCartPairsByCartId(cartId)
                .stream()
                .mapToDouble(productCart -> productCart.getQuantity() * productService.findById(productCart.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productCart.getProductId())).getPrice())
                .sum();
    }

}

package org.cem.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.cem.dto.response.CartGetCartItemsResponseDto;
import org.cem.entity.Cart;
import org.cem.entity.Product;
import org.cem.entity.Product_Cart;
import org.cem.repository.CartRepository;
import org.cem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }

    public void emptyCart(Long cartId) {
        Cart cart = getCart(cartId);
        cart.getProductCartIds().clear();
        cart.setTotalPrice(0.0);
        productCartService.deleteAll(productCartService.findAllByCartId(cartId));
        save(cart);
    }

    @Transactional
    public Cart addProductToCart(Long productId, Long cartId, Integer quantity) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + cartId));

        Optional<Product_Cart> optionalProductCart = productCartService
                .findOptionalByCartIdAndProductId(cartId, productId);

        if (optionalProductCart.isPresent()) {
            Product_Cart productCart = optionalProductCart.get();
            productCart.setQuantity(optionalProductCart.get().getQuantity() + quantity);
            cart.setTotalPrice(cart.getTotalPrice() + product.getPrice() * quantity);
            return cartRepository.save(cart);
        }

        Product_Cart productCart = Product_Cart.builder()
                .cartId(cartId)
                .productId(productId)
                .quantity(quantity)
                .build();
        Product_Cart savedProduct_Cart = productCartService.save(productCart);

        cart.getProductCartIds().add(savedProduct_Cart.getId());
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice() * quantity);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeProductFromCart(Long productId, Long cartId) {

        Product product = productService.findProductByIdElseThrowException(productId);

        Cart cart = findCartByIdOrElseThrowException(cartId);

        Optional<Product_Cart> optionalProductCart = productCartService
                .findOptionalByCartIdAndProductId(cartId, productId);

        if (optionalProductCart.isPresent()) {
            Product_Cart productCart = optionalProductCart.get();
            productCartService.delete(productCart);
            cart.getProductCartIds().remove(productId);
            cart.setTotalPrice(cart.getTotalPrice() - product.getPrice() * productCart.getQuantity());
            return cartRepository.save(cart);
        } else {
            throw new InternalError("Product not found in cart");
        }
    }

    /**
     * The purpose of this method is to return the list of products in the cart
     * If user moves to the order phase, the order will not be affected of change in the prices of the products.
     *
     * @param cartId - cartId
     * @return <List<Product>>
     */
    @Transactional
    public List<CartGetCartItemsResponseDto> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + cartId));
        return getProductCartPairsByCartId(cartId)
                .stream()
                .map(productCart -> {
                    CartGetCartItemsResponseDto dto = CartGetCartItemsResponseDto.builder().build();
                    Integer quantity = productCart.getQuantity();
                    dto.setQuantity(quantity);
                    dto.setProductId(productCart.getProductId());
                    Product product = productService.findProductByIdElseThrowException(productCart.getProductId());
                    dto.setProductName(product.getName());
                    dto.setProductPrice(product.getPrice());
                    dto.setTotalPrice(product.getPrice() * quantity);
                    return dto;
                }).toList();
    }

    public List<Product_Cart> getProductCartPairsByCartId(Long cartId) {
        return findCartByIdOrElseThrowException(cartId)
                .getProductCartIds()
                .stream()
                .map(productCartId -> productCartService.findById(productCartId)
                        .orElseThrow(() -> new EntityNotFoundException("ProductCart not found with id: " + productCartId)))
                .collect(Collectors.toList());
    }

    public Double calculateTotalPrice(Long cartId) {
        return getProductCartPairsByCartId(cartId)
                .stream()
                .mapToDouble(productCart -> productCart.getQuantity() * productService.findById(productCart.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productCart.getProductId())).getPrice())
                .sum();
    }

    public Cart findCartByIdOrElseThrowException(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + id));
    }

}

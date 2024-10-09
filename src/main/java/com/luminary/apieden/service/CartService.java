package com.luminary.apieden.service;

import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.CartItemRequest;
import com.luminary.apieden.repository.CartItemRepository;
import com.luminary.apieden.repository.CartRepository;
import com.luminary.apieden.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    public List<CartItem> findCartItemsByCartId(String cartId) {
        log.info("Finding cartItems by cart id: {}", cartId);
        return cartItemRepository.findCartItemsByCartId(Long.parseLong(cartId));
    }
    public CartItem register(CartItemRequest request) {
        log.info("Finding product described in cardItem.productId. ID: {}", request.getProductsId());
        Product product = productRepository.findById(request.getProductsId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
        CartItem cartItem = CartItem.builder()
                .cartId(request.getCartId())
                .product(product)
                .build();
        cartItemRepository.save(cartItem);
        return cartItem;
    }
    public void delete(String cartItemId) {
        cartItemRepository.deleteById(Long.valueOf(cartItemId));
    }
}

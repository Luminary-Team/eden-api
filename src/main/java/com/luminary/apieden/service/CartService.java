package com.luminary.apieden.service;

import com.luminary.apieden.mapper.CartMapper;
import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.CartItemRequest;
import com.luminary.apieden.model.response.CartItemResponse;
import com.luminary.apieden.repository.CartItemRepository;
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
    private final CartMapper cartMapper;
    public List<CartItemResponse> findCartItemsByCartId(String cartId) {
        log.info("Finding cartItems by cart id: {}", cartId);
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(Long.parseLong(cartId));
        return cartItemList.stream()
                .map(cartItem -> {
                    Product product = productRepository.findById(cartItem.getProductId()).get();
                    return cartMapper.toRegisterCartItem(cartItem, product);
                })
                .toList();
    }
    public CartItemResponse register(CartItemRequest request) {
        log.info("Finding product described in cardItem.productId. ID: {}", request.getProductsId());
        Product product = productRepository.findById(request.getProductsId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
        if (!cartItemRepository
                .findCartItemByCartIdAndProductId(request.getCartId(), product.getId())
                .isEmpty()
        ) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "Produto já cadastrado nesse carrinho");
        }
        CartItem cartItem = CartItem.builder()
                .cartId(request.getCartId())
                .productId(product.getId())
                .build();
        cartItemRepository.save(cartItem);
        return cartMapper.toRegisterCartItem(cartItem, product);
    }
    public void delete(String cartItemId) {
        cartItemRepository.deleteById(Long.valueOf(cartItemId));
    }
}

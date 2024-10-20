package com.luminary.apieden.service;

import com.luminary.apieden.mapper.CartMapper;
import com.luminary.apieden.model.database.Cart;
import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.procedure.TotalSaleProcedure;
import com.luminary.apieden.model.request.CartItemRequest;
import com.luminary.apieden.model.response.CartItemResponse;
import com.luminary.apieden.model.response.FindCartItemResponse;
import com.luminary.apieden.model.response.RegisterCartItemResponse;
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
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final TotalSaleProcedure totalSaleProcedure;
    private final CartMapper cartMapper;
    public FindCartItemResponse findCartItemsByCartId(String cartId) {
        log.info("Finding cartItems by cart id: {}", cartId);
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(Long.parseLong(cartId));
        List<CartItemResponse> cartItemResponseList = cartItemList.stream()
                .map(cartItem -> {
                    Product product = productRepository.findById(cartItem.getProductId()).get();
                    return cartMapper.toCartItemResponse(cartItem, product);
                })
                .toList();
        Cart cart = cartRepository.findById(Long.parseLong(cartId))
                .orElseThrow(() -> {
                    log.error("[Cart Service] Cart could not be found to return totalSale");
                    return new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Não conseguiu fazer a consulta para totalSale.");
                });
        return cartMapper.toFindCartItemResponse(cartItemResponseList, cart.getTotalSale());
    }
    public RegisterCartItemResponse register(CartItemRequest request) {
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
        cartRepository.totalSaleCalc((int) cartItem.getCartId());
        return cartMapper.toRegisterCartItemResponse(cartItem, product);
    }
    public void delete(String cartItemId) {
        CartItem cartItem = cartItemRepository.findById(Long.valueOf(cartItemId))
                        .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "ID não encontrado"));
        cartRepository.totalSaleCalc((int) cartItem.getCartId());
        cartItemRepository.deleteById(Long.valueOf(cartItemId));
    }
}

package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.CartContract;
import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.request.CartItemRequest;
import com.luminary.apieden.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController implements CartContract {
    private final CartService cartService;
    @GetMapping("/getCartItems/{cartId}")
    public ResponseEntity<List<CartItem>> findCartItems(@PathVariable String cartId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartItemsByCartId(cartId));
    }
    @PostMapping("/register")
    public ResponseEntity<CartItem> register(@RequestBody @Valid CartItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.register(request));
    }
    @DeleteMapping("/deleteCartItem/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String cartItemId) {
        cartService.delete(cartItemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.luminary.apieden.controller;

import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.model.response.OrderResponse;
import com.luminary.apieden.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/registerOrder")
    public ResponseEntity<OrderResponse> register(@RequestBody @Valid RegisterOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.registerOrder(request));
    }
}

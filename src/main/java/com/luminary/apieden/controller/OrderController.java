package com.luminary.apieden.controller;

import com.luminary.apieden.model.database.Order;
import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.service.OrderService;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/register")
    public ResponseEntity<Order> register(@RequestBody RegisterOrderRequest request) {

    }
}

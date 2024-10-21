package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.OrderContract;
import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.model.response.FindAllOrderResponse;
import com.luminary.apieden.model.response.OrderResponse;
import com.luminary.apieden.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController implements OrderContract {
    private final OrderService orderService;
    @PostMapping("/registerOrder")
    public ResponseEntity<OrderResponse> register(@RequestBody @Valid RegisterOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.registerOrder(request));
    }
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<FindAllOrderResponse> getAll(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll(userId));
    }
}

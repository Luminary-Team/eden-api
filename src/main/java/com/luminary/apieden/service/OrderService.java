package com.luminary.apieden.service;

import com.luminary.apieden.mapper.OrderMapper;
import com.luminary.apieden.model.database.Order;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.repository.OrderItemRepository;
import com.luminary.apieden.repository.OrderRepository;
import com.luminary.apieden.repository.PaymentTypeRepository;
import com.luminary.apieden.repository.StatusOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final PaymentTypeRepository paymentTypeRepository;
    private final StatusOrderRepository statusOrderRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    public Order register(RegisterOrderRequest request) {
        paymentTypeRepository.findById(request.getPaymentTypeId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "'Tipo de pagamento' não encontrado"));
        statusOrderRepository.findById(request.getStatusOrderId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "'Status do pedido' não encontrado"));
        Order order = orderMapper.toOrder(request);
        order.setOrderDate(LocalDate.now());
        orderRepository.save(order);
        return order;
    }
}

package com.luminary.apieden.service;

import com.luminary.apieden.mapper.OrderMapper;
import com.luminary.apieden.model.database.Cart;
import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.database.Order;
import com.luminary.apieden.model.database.OrderItem;
import com.luminary.apieden.model.database.PaymentType;
import com.luminary.apieden.model.database.StatusOrder;
import com.luminary.apieden.model.enums.StatusOrderEnum;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.model.response.OrderResponse;
import com.luminary.apieden.repository.CartItemRepository;
import com.luminary.apieden.repository.CartRepository;
import com.luminary.apieden.repository.OrderItemRepository;
import com.luminary.apieden.repository.OrderRepository;
import com.luminary.apieden.repository.PaymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final PaymentTypeRepository paymentTypeRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;

    public OrderResponse registerOrder(RegisterOrderRequest request) {
        PaymentType paymentType = paymentTypeRepository.findById(request.getPaymentTypeId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "'Tipo de pagamento' não encontrado"));
        StatusOrder statusOrder = StatusOrder.builder()
                .id(StatusOrderEnum.ENTREGUE.getId())
                .status(StatusOrderEnum.ENTREGUE.getStatus())
                .description(StatusOrderEnum.ENTREGUE.getDescription())
                .build();
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Carrinho não encontrado."));
        Order order = orderMapper.toOrder(request, StatusOrderEnum.ENTREGUE.getId(), LocalDate.now(), cart.getUserId(), cart.getTotalSale());
        orderRepository.save(order);
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(request.getCartId());
        if (!cartItemList.isEmpty()) {
            cartItemList
                    .forEach(cartItem -> {
                        OrderItem orderItem = OrderItem.builder()
                                .orderId(order.getId())
                                .productId(cartItem.getProductId())
                                .build();
                        cartItemRepository.deleteCartItemsByProductId(cartItem.getProductId());
                        orderItemRepository.save(orderItem);
                    });
            cartRepository.totalSaleCalc((int) request.getCartId());
            return orderMapper.toOrderResponse(order, statusOrder, paymentType);
        }
        throw new HttpError(HttpStatus.BAD_REQUEST, "Compra não pôde ser finalizada, carrinho vazio");
    }
}

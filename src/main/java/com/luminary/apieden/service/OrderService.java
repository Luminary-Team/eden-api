package com.luminary.apieden.service;

import com.luminary.apieden.mapper.OrderMapper;
import com.luminary.apieden.model.database.Cart;
import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.database.Order;
import com.luminary.apieden.model.database.OrderItem;
import com.luminary.apieden.model.database.PaymentType;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.database.StatusOrder;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.enums.StatusOrderEnum;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.model.response.FindAllOrderResponse;
import com.luminary.apieden.model.response.OrderResponse;
import com.luminary.apieden.repository.CartItemRepository;
import com.luminary.apieden.repository.CartRepository;
import com.luminary.apieden.repository.OrderItemRepository;
import com.luminary.apieden.repository.OrderRepository;
import com.luminary.apieden.repository.PaymentTypeRepository;
import com.luminary.apieden.repository.ProductRepository;
import com.luminary.apieden.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final PaymentTypeRepository paymentTypeRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
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
        User user = userRepository.findById(cart.getUserId())
                .orElseThrow(() -> new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado"));
        Order order = orderMapper.toOrder(request, StatusOrderEnum.ENTREGUE.getId(), LocalDate.now(), cart.getUserId(), cart.getTotalSale());
        orderRepository.save(order);
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(request.getCartId());
        if (!cartItemList.isEmpty()) {
            cartItemList
                    .forEach(cartItem -> {
                        Product product = productRepository.findById(cartItem.getProductId())
                                .orElseThrow(() -> {
                                    log.error("Product Id {} not found", cartItem.getProductId());
                                    return new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Produto não encontrado para compra, contate o suporte");
                                });
                        user.getFavoritesProducts().remove(product);
                        userRepository.save(user);
                        OrderItem orderItem = OrderItem.builder()
                                .orderId(order.getId())
                                .product(product)
                                .build();
                        cartItemRepository.deleteCartItemsByProductId(cartItem.getProductId());
                        orderItemRepository.save(orderItem);
                    });
            cartRepository.totalSaleCalc((int) request.getCartId());
            return orderMapper.toOrderResponse(order, statusOrder, paymentType);
        }
        throw new HttpError(HttpStatus.BAD_REQUEST, "Compra não pôde ser finalizada, carrinho vazio");
    }

    public FindAllOrderResponse getAll(String userId) {
        FindAllOrderResponse findAllOrderResponse = new FindAllOrderResponse();
        List<Order> orderList = orderRepository.findOrderByUserId(Long.parseLong(userId));
        if (!orderList.isEmpty()) {
            orderList.stream()
                    .map(order -> orderItemRepository.findOrderItemsByOrderId(order.getId()))
                    .forEach(orderItems -> orderItems.forEach(orderItem ->
                            findAllOrderResponse.add(orderItem.getProduct())
                    ));
            return findAllOrderResponse;
        }
        throw new HttpError(HttpStatus.BAD_REQUEST, "Nenhum pedido realizado ainda");
    }
}

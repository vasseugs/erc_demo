package com.example.erc_demo.service.impl;

import com.example.erc_demo.model.OrderDto;
import com.example.erc_demo.repository.OrderItemRepository;
import com.example.erc_demo.repository.OrderRepository;
import com.example.erc_demo.service.OrderService;
import com.example.erc_demo.service.UserService;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final UserService userService;

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;

  public void createOrder(OrderDto orderDto) {
    checkUserExists(orderDto.getUserId());

    var orderEntity = orderDto.toEntity();
    orderEntity.setCreatedAt(Timestamp.from(Instant.now()));
    var savedOrder = orderRepository.save(orderEntity);

    for (var orderItemDto : orderDto.getOrderItems()) {
      var orderItemEntity = orderItemDto.toEntity();
      orderItemEntity.setOrderId(savedOrder.getId());
      orderItemRepository.save(orderItemEntity);
    }
  }

  private void checkUserExists(Long userId) {
    userService.checkUserExists(userId);
  }
}

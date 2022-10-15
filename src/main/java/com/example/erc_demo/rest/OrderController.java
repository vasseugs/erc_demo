package com.example.erc_demo.rest;

import com.example.erc_demo.model.OrderDto;
import com.example.erc_demo.service.OrderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Отвечает за работу с заявками
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "orders/")
public class OrderController {

  private final OrderService orderService;

  @PostMapping(path = "create")
  public ResponseEntity<Void> createOrder(@RequestBody @Valid OrderDto orderDto) {
    orderService.createOrder(orderDto);
    return ResponseEntity.ok().build();
  }
}

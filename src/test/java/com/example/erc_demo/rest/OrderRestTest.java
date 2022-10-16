package com.example.erc_demo.rest;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.erc_demo.initializer.AbstractTestContainers;
import com.example.erc_demo.model.OrderDto;
import com.example.erc_demo.model.OrderItemDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;

public class OrderRestTest extends AbstractTestContainers {

  @Test
  @Sql(scripts = "/sql/user.sql")
  @SqlMergeMode(value = MergeMode.MERGE)
  void createOrder() {
    var orderItem1 = OrderItemDto.builder()
        .productName("paper")
        .amount(1)
        .build();

    var orderItem2 = OrderItemDto.builder()
        .productName("pen")
        .amount(2)
        .build();

    var orderDto = OrderDto.builder()
        .orderItems(List.of(orderItem1, orderItem2))
        .address("London")
        .phoneNumber("+71234567890")
        .userId(1L)
        .build();

    var createNewOrder = testRestTemplate.exchange(
        "/orders/create", HttpMethod.POST,
        new HttpEntity<>(orderDto), String.class);
    assertThat(createNewOrder.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}

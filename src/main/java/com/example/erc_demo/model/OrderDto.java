package com.example.erc_demo.model;

import com.example.erc_demo.entity.OrderEntity;
import java.util.List;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

  private List<OrderItemDto> orderItems;
  private String address;
  @Pattern(regexp = "^\\+7[\\d]{10}$", message = "Incorrect phone number")
  private String phoneNumber;
  private Long userId;

  public OrderEntity toEntity() {
    return OrderEntity.builder()
        .address(this.address)
        .phoneNumber(this.phoneNumber)
        .userId(this.userId)
        .build();
  }
}

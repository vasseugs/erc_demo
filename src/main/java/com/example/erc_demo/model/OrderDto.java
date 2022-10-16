package com.example.erc_demo.model;

import com.example.erc_demo.entity.OrderEntity;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {

  @Valid
  @NotEmpty(message = "The order must contain at least 1 item")
  private List<OrderItemDto> orderItems;

  @NotBlank(message = "Address is empty")
  private String address;

  @Pattern(regexp = "^\\+7[\\d]{10}$", message = "Incorrect phone number")
  private String phoneNumber;

  @NotNull(message = "User id is not specified")
  private Long userId;

  public OrderEntity toEntity() {
    return OrderEntity.builder()
        .address(this.address)
        .phoneNumber(this.phoneNumber)
        .userId(this.userId)
        .build();
  }
}

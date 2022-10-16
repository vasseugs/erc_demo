package com.example.erc_demo.model;

import com.example.erc_demo.entity.OrderItemEntity;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemDto {

  private String productName;
  @Min(value = 1, message = "The amount should at least be 1")
  private Integer amount;

  public OrderItemEntity toEntity() {
    return OrderItemEntity.builder()
        .productName(this.productName)
        .amount(this.amount)
        .build();
  }
}

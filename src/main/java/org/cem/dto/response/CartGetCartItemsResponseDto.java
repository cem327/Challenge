package org.cem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartGetCartItemsResponseDto {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double totalPrice;
}

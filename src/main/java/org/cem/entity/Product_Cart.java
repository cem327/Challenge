package org.cem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl_cart_product")
public class Product_Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;

    @CreatedDate
    @Builder.Default
    private Long createdAt = System.currentTimeMillis();
    @LastModifiedDate
    @Builder.Default
    private Long updatedAt = System.currentTimeMillis();
}

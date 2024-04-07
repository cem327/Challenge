package org.cem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

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


    @Temporal(TemporalType.DATE)
    @CreatedDate
    private LocalDate createdDate;
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private LocalDate modifiedDate;
}

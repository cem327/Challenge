package org.cem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @Builder.Default
    private Double totalPrice = 0.0;
    @ElementCollection
    private List<Long> productCartIds;


    @CreatedDate
    @Builder.Default
    private Long createdAt = System.currentTimeMillis();
    @LastModifiedDate
    @Builder.Default
    private Long updatedAt = System.currentTimeMillis();


    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", totalPrice=" + totalPrice + ", productCartIds=" + productCartIds + ", createdDate=" + createdAt + ", modifiedDate=" + updatedAt + '}';

    }

}

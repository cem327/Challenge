package org.cem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cem.utility.enums.Status;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private Long customerId;
    @ElementCollection
    private List<Long> productOrderIds;
    private Double totalPrice;


    @CreatedDate
    @Builder.Default
    private Long createdAt = System.currentTimeMillis();
    @LastModifiedDate
    @Builder.Default
    private Long updatedAt = System.currentTimeMillis();

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", customer (ID) =" + customerId +
                " - WARNING: Circular reference omitted" +
                ", cartOrderIds=" + productOrderIds +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

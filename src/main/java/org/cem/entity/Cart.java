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
@Entity(name = "tbl_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;
    @Builder.Default
    private Double totalPrice = 0.0;
    @ElementCollection
    private List<Long> productCartIds;


    @Temporal(TemporalType.DATE)
    @CreatedDate
    private LocalDate createdDate;
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private LocalDate modifiedDate;

}

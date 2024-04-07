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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.cem.utility.enums.Status.PENDING;
import static org.cem.utility.enums.Status.PLACED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    private Customer customer;
    @ElementCollection
    private List<Long> cartOrderIds;
    private Double totalPrice;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private LocalDate createdDate;
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private LocalDate modifiedDate;

}

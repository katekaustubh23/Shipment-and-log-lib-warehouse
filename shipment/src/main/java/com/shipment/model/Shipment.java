package com.shipment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments", schema = "shipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String shippingAddress;

    private String status; // PENDING, SHIPPED, DELIVERED

    private LocalDateTime shippedAt;

    private LocalDateTime expectedDelivery;

}

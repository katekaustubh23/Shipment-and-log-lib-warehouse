package com.shipment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShipmentRequest {
    private Long orderId;
    private String shippingAddress;
    private LocalDateTime expectedDelivery;
}

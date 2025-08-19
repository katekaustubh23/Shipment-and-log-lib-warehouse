package com.shipment.service;

import com.shipment.dto.ShipmentRequest;
import com.shipment.model.Shipment;
import com.shipment.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public Shipment createShipment(ShipmentRequest request) {
        Shipment shipment = Shipment.builder()
                .orderId(request.getOrderId())
                .shippingAddress(request.getShippingAddress())
                .expectedDelivery(request.getExpectedDelivery())
                .shippedAt(LocalDateTime.now())
                .status("PENDING")
                .build();
        return shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getById(Long id) {
        return shipmentRepository.findById(id).orElse(null);
    }
}

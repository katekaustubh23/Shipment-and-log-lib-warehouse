package com.shipment.service;

import com.shipment.dto.ShipmentRequest;
import com.shipment.model.Shipment;

import java.util.List;

public interface ShipmentService {
    Shipment createShipment(ShipmentRequest request);
    List<Shipment> getAll();
    Shipment getById(Long id);
}

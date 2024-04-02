package com.devsuperior.components_dependencyinjection.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.components_dependencyinjection.entities.Order;

@Service
public class OrderService {
	@Autowired
	ShippingService shippingService;
	
	public Double total(Order order) {
		Double shipment = shippingService.shipment(order);
		Double discount = order.getBasic() * (order.getDiscount()/100);
		Double totalValue = order.getBasic() - discount + shipment;
		return totalValue;
	}
}

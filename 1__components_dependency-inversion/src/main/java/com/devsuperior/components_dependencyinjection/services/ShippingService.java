package com.devsuperior.components_dependencyinjection.services;

import org.springframework.stereotype.Service;

import com.devsuperior.components_dependencyinjection.entities.Order;

@Service
public class ShippingService {

	public Double shipment(Order order) {
		Double basicValue = order.getBasic();
		Double shipment = 0.0;
		
		if(basicValue<100) {
			shipment = 20.0;
		}else if(basicValue<200){
			shipment = 12.0;
		}
		return shipment;
	}
}
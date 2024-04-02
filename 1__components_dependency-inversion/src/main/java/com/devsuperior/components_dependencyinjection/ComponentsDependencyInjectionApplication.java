package com.devsuperior.components_dependencyinjection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.components_dependencyinjection.entities.Order;
import com.devsuperior.components_dependencyinjection.services.OrderService;

@SpringBootApplication
public class ComponentsDependencyInjectionApplication implements CommandLineRunner{
	
	@Autowired
	OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(ComponentsDependencyInjectionApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// Instancia os pedidos para teste
		
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1034, 150.0, 20.0));
		orders.add(new Order(2282, 800.0, 10.0));
		orders.add(new Order(1309, 95.90, 0.0));
		
		// Itera sobre a lista de pedidos, exibindo o código/valor total de cada um
		orders.forEach(order->{
			Double totalValue = orderService.total(order);
			System.out.printf("\nOrder code: %d\n", order.getCode());
			System.out.printf("TotalValue: R$ %.2f\n", totalValue);			
		});
	
	}

}
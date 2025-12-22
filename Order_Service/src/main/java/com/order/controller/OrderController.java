package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Order;
import com.order.service.OrderService;

@RestController
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/place-order")
	public Order placeOrder(@RequestBody Order order) {
		log.info("Inside controller to place the order:"+order);
		return orderService.createOrder(order);
	}
	
	@GetMapping("/getAllOrderByUser")
	public List<Order> getAllOrderByUser(@RequestParam long userId){
		log.info("Inside controller to fetch all order for User: "+userId);
		return orderService.getAllOrderByUserId(userId);
	}
	
	@GetMapping("/getOrder/{orderId}")
	public Order getOrderById(@PathVariable Long orderId) {
		log.info("Inside controller to fetch order by Order Id: "+orderId);
		return orderService.getOrderById(orderId);
	}
	
}

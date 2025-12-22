package com.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.dto.UserDto;
import com.order.entity.Order;
import com.order.exception.OrderException;
import com.order.repo.IOrderRepo;

@Service
public class OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private IOrderRepo orderRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Order createOrder(Order order) {
		log.info("Order is placed for UserId: "+order.getUserId());
		
		try {
			
			UserDto user = restTemplate.getForObject("http://localhost:9000/get-user/{id}",UserDto.class, order.getUserId());
			
			if(user==null) {
				log.error("User with Id: "+order.getUserId()+" does not exists...");
				throw new OrderException("User with Id: "+order.getUserId()+" does not exists...");
			}
			
			order.setStatus("Placed");
			Order savedOrder=orderRepo.save(order);
			log.debug("Order created successfully for User: "+order.getUserId());
			return savedOrder;
			
		}catch (Exception e) {
			log.error("Failed to Placed order for user id: "+order.getUserId());
			throw new OrderException("Failed to create Order for User id: "+order.getUserId(),e);
		}
	}
	public List<Order> getAllOrderByUserId(Long userId){
		
		log.info("Fetching all order for User: "+userId);
		try {
			List<Order> allOrder = orderRepo.getOrderByUserId(userId);
			return allOrder;
		} catch (Exception e) {
			log.debug("Failed to get orders for User Id: "+userId);
			throw new OrderException("Failed to get orders for User Id: "+userId,e);
		}
	}
		
	public Order getOrderById(Long orderId) {
		
		return orderRepo.findById(orderId)
				.orElseThrow(() -> {
					log.debug("Failed to get orders for orderId: "+orderId);
					 throw new OrderException("Failed to get orders for orderId: "+orderId);
				});
	}
		
	}



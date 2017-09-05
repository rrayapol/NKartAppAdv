package com.nisum.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
	
	public List<Order> findOrderByUsername(String username);

}

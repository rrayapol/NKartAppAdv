package com.nisum.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.nisum.dao.MongoDao;
import com.nisum.model.Message;
import com.nisum.model.Order;
import com.nisum.model.Product;

@Service
public class AppMangerService {

	@Autowired
	private MongoDao mongoDAO;

	private String uname = "username";

	public List<Order> getOrders(HttpSession session) {

		return mongoDAO.getOrdersByUserName(session.getAttribute(uname).toString());
	}

	public List<Product> getProducts(HttpSession session) {
		List<Product> products = mongoDAO.getProducts();
		if (session.getAttribute(uname) != null) {
			List<Order> orders = mongoDAO.getOrdersByUserName(session.getAttribute(uname).toString());
			Set<String> productIds = new HashSet<>();
			for (Order order : orders) {
				productIds.add(order.getProductId());
			}

			for (Product product : products) {

				if (productIds.contains(product.getProductId())) {
					product.setOrdered(true);
				}
			}
		}
		return products;
	}

	public Message createOrder(@RequestBody Product product, HttpSession session) {

		String username = (String) session.getAttribute(uname);
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = now.format(formatter);

		Order order = new Order(product.getProductId(), product.getName(), 1, product.getPrice(), formatDateTime,
				username, product.getImageUrl());
		mongoDAO.createOrder(order);
		return new Message("success");
	}

	public Message removeOrder(String orderId) {

		try {
			mongoDAO.removeOrder(orderId);
			return new Message("success");
		} catch (Exception e) {
			return new Message("Failed");
		}
	}



}

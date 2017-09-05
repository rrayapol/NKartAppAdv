package com.nisum.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.model.Message;
import com.nisum.model.Order;
import com.nisum.model.Product;
import com.nisum.service.AppMangerService;

@RestController
public class AppManageController {


	@Autowired
	AppMangerService appService;

	@GetMapping("/getOrders")
	@ResponseBody
	public List<Order> getOrders(HttpSession session) {
		List<Order> orderList = appService.getOrders(session);
		return orderList;

	}

	@GetMapping("/getProducts")
	@ResponseBody
	public List<Product> getProducts(HttpSession session) {

		return appService.getProducts(session);
	}

	@PostMapping("/createOrder")
	@ResponseBody
	public Message createOrder(@RequestBody Product product, HttpSession session) {

		return appService.createOrder(product, session);
	}

	@PostMapping("/removeOrder")
	@ResponseBody
	public Message removeOrder(@RequestParam(value = "orderId") String orderId) {

		return appService.removeOrder(orderId);
	}

}
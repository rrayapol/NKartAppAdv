package com.test.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nisum.controller.AppManageController;
import com.nisum.model.Message;
import com.nisum.model.Order;
import com.nisum.model.Product;
import com.nisum.service.AppMangerService;

@RunWith(MockitoJUnitRunner.class)
public class AppManageControllerTest {

	@InjectMocks
	AppManageController appController = new AppManageController();

	@Mock
	AppMangerService appService;

String name = “rakesh”;

	@Test
	public void testGetOrders() {

		List<Order> orders = new ArrayList<>();

		Order o1 = new Order();
		Order o2 = new Order();

		orders.add(o1);
		orders.add(o2);
		when(appService.getOrders(null)).thenReturn(orders);

		List<Order> result = appController.getOrders(null);

		Assert.assertEquals(result.size(), 2);

	}

	@Test
	public void testGetProducts() {

		List<Product> products = new ArrayList<>();

		Product p1 = new Product();
		Product p2 = new Product();

		products.add(p1);
		products.add(p2);
		when(appService.getProducts(null)).thenReturn(products);

		List<Product> result = appController.getProducts(null);

		Assert.assertEquals(result.size(), 2);

	}

	@Test
	public void testCreateOrder() {

		Product p = new Product();
		when(appService.createOrder(p, null)).thenReturn(new Message("success"));

		Message result = appController.createOrder(p, null);

		Assert.assertEquals(result.getMessage(), "success");

	}

	@Test
	public void testRemoveOrder() {
		when(appService.removeOrder("test123")).thenReturn(new Message("success"));
		Message result = appController.removeOrder("test123");

		Assert.assertEquals(result.getMessage(), "success");
	}

}

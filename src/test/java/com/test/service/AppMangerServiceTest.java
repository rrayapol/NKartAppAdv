package com.test.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner.Silent;

import com.nisum.dao.MongoDao;
import com.nisum.model.Message;
import com.nisum.model.Order;
import com.nisum.model.Product;
import com.nisum.service.AppMangerService;

@RunWith(Silent.class)
public class AppMangerServiceTest {

	@InjectMocks
	AppMangerService appService = new AppMangerService();

	@Mock
	private MongoDao mongoDAO;

	@Test
	public void testGetOrders() {

		List<Order> orderList = new ArrayList<>();

		Order o1 = new Order();
		Order o2 = new Order();

		orderList.add(o1);
		orderList.add(o2);

		HttpSession httpSession = mock(HttpSession.class);

		when(mongoDAO.getOrdersByUserName("nisum")).thenReturn(orderList);
		when(httpSession.getAttribute("username")).thenReturn("nisum");
		List<Order> result = appService.getOrders(httpSession);

		Assert.assertEquals(result.size(), 2);

	}

	@Test
	public void testGetProducts() {

		List<Order> orderList = new ArrayList<>();
		List<Product> productList = new ArrayList<>();

		Order o1 = new Order();
		o1.setProductId("P123");
		Order o2 = new Order();
		o1.setProductId("P321");
		Product p1 = new Product();
		p1.setProductId("P123");
		Product p2 = new Product();
		p1.setProductId("P321");
		productList.add(p1);
		productList.add(p2);

		orderList.add(o1);
		orderList.add(o2);

		HttpSession httpSession = mock(HttpSession.class);

		when(mongoDAO.getProducts()).thenReturn(productList);
		when(httpSession.getAttribute("username")).thenReturn("nisum");
		when(mongoDAO.getOrdersByUserName("nisum")).thenReturn(orderList);
		List<Product> result = appService.getProducts(httpSession);

		Assert.assertEquals(result.size(), 2);

	}

	@Test
	public void testCreateOrder() {

		Product p1 = new Product();
		p1.setProductId("P123");

		Order order = new Order();

		HttpSession httpSession = mock(HttpSession.class);

		when(mongoDAO.createOrder(order)).thenReturn(true);
		when(httpSession.getAttribute("username")).thenReturn("nisum");
		Message result = appService.createOrder(p1, httpSession);

		Assert.assertEquals(result.getMessage(), "success");

	}
	
	@Test
	public void testRemoveOrderSuccess() {

		doNothing().when(mongoDAO).removeOrder("nisum123");
		Message result = appService.removeOrder("nisum123");

		Assert.assertEquals(result.getMessage(), "success");

	}
	
	@Test
	public void testRemoveOrderFailure() {

		doThrow(new RuntimeException()).when(mongoDAO).removeOrder("nisum123");

		Message result = appService.removeOrder("nisum123");

		Assert.assertEquals(result.getMessage(), "Failed");

	}

}

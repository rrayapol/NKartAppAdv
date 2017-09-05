package com.test.dao;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.nisum.dao.MongoDao;
import com.nisum.dao.OrderRepository;
import com.nisum.dao.ProductRepository;
import com.nisum.dao.UserRepository;
import com.nisum.model.Order;
import com.nisum.model.Product;
import com.nisum.model.User;

@RunWith(MockitoJUnitRunner.class)
public class MongoDaoTest {

	@InjectMocks
	MongoDao mDao = new MongoDao();

	@Mock
	UserRepository userRepository;
	@Mock
	ProductRepository productRepository;
	@Mock
	OrderRepository orderRepository;

	@Test
	public void testCreateUserSuccess() {

		User user = new User();
		user.setUsername("nisum123");
		when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
		when(userRepository.save(user)).thenReturn(user);
		boolean result = mDao.createUser(user);

		Assert.assertEquals(result, true);

	}

	@Test
	public void testCreateUserFailed() {

		User user = new User();
		when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		boolean result = mDao.createUser(user);

		Assert.assertEquals(result, false);

	}

	@Test
	public void testCreateProduct() {

		Product product = new Product();
		product.setProductId("test123");
		when(productRepository.save(product)).thenReturn(product);
		boolean result = mDao.createProduct(product);

		Assert.assertEquals(result, true);

	}

	@Test
	public void testCreateOrder() {

		Order order = new Order();
		order.setOrderId("test123");
		when(orderRepository.save(order)).thenReturn(order);
		boolean result = mDao.createOrder(order);

		Assert.assertEquals(result, true);

	}

	@Test
	public void testGetOrders() {

		List<Order> orderList = new ArrayList<>();
		Order o1 = new Order();
		Order o2 = new Order();
		orderList.add(o1);
		orderList.add(o2);
		when(orderRepository.findAll()).thenReturn(orderList);
		List<Order> result = mDao.getOrders();

		Assert.assertEquals(result.size(), 2);

	}

	@Test
	public void testGetOrdersByUserName() {

		List<Order> orderList = new ArrayList<>();
		Order o1 = new Order();
		Order o2 = new Order();
		orderList.add(o1);
		orderList.add(o2);
		when(orderRepository.findOrderByUsername("nisum123")).thenReturn(orderList);
		List<Order> result = mDao.getOrdersByUserName("nisum123");

		Assert.assertEquals(result.size(), 2);

	}

	@Test
	public void testAuthenticateSuccess() {

		User user = new User();
		user.setConfirmed(true);
		when(userRepository.findByUsernameAndPassword("nisum123", "123")).thenReturn(user);
		Map<String,Boolean> result = mDao.authenticate("nisum123", "123");

		Assert.assertEquals(result.get("validUser"), true);

	}


	
	@Test
	public void testAuthenticateFailure() {

		User user = new User();
		user.setConfirmed(false);

		when(userRepository.findByUsernameAndPassword("nisum123", "123")).thenReturn(user);
		Map<String,Boolean>  result = mDao.authenticate("nisum123", "123");

		Assert.assertEquals(result.get("confirmUser"), false);
	}

	@Test
	public void testGetProducts() {

		List<Product> productList = new ArrayList<>();
		Product p1 = new Product();
		Product p2 = new Product();
		productList.add(p1);
		productList.add(p2);
		when(productRepository.findAll()).thenReturn(productList);
		List<Product> result = mDao.getProducts();

		Assert.assertEquals(result.size(), 2);

	}

	@Test
	public void testConfirmUserSuccess() {

		User user = new User();
		user.setSecureToken("1234");
		when(userRepository.findByUsernameAndPassword("nisum", "123")).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		boolean result = mDao.confirmUser("nisum", "123", "1234");

		Assert.assertEquals(result, true);

	}

	@Test
	public void testConfirmUserFailure() {

		when(userRepository.findByUsernameAndPassword("nisum", "123")).thenReturn(null);
		boolean result = mDao.confirmUser("nisum", "123", "1234");

		Assert.assertEquals(result, false);

	}
	
	
}

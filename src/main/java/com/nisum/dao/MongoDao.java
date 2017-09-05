package com.nisum.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.nisum.model.Order;
import com.nisum.model.Product;
import com.nisum.model.User;

/**
 * DAO Class which handles all data layer requests and forwards to respective repositories
 * @author nisum
 *
 */
@Scope("singleton")
@Repository
public class MongoDao {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderRepository orderRepository;

	

	public boolean createUser(User user) {
		if (userRepository.findByUsername(user.getUsername()) == null) {
			user.setConfirmed(false);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public boolean createProduct(Product product) {
		productRepository.save(product);
		return true;
	}

	public boolean createOrder(Order order) {
		orderRepository.save(order);
		return true;
	}

	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	public List<Order> getOrdersByUserName(String username) {
		return orderRepository.findOrderByUsername(username);
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	/**
	 * Returns true if details exist
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public Map<String,Boolean> authenticate(String userName, String password) {
		User user = userRepository.findByUsernameAndPassword(userName, password);
		
		Map<String,Boolean> resultMap = null;
		if (user != null && user.isConfirmed()) {
			resultMap = new HashMap<String,Boolean>();
			resultMap.put("validUser", true);
			resultMap.put("confirmUser", true);
		}else if(user != null) {
			resultMap = new HashMap<String,Boolean>();
			resultMap.put("validUser", true);
			resultMap.put("confirmUser", false);
		}
		return resultMap;
	}
	
	public boolean confirmUser(String userName, String password,String secureToken) {
		
		User user = userRepository.findByUsernameAndPassword(userName, password);
		if (user != null && user.getSecureToken().equals(secureToken)) {
			user.setConfirmed(true);
			userRepository.save(user);
			return true;
		}
		return false;
	}


	
	public void removeOrder(String orderId) {
		orderRepository.delete(orderId);
	}

}

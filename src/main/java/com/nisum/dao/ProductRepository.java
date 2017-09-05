package com.nisum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	

}

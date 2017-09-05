package com.nisum.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	public List<User> findByFirstName(String firstName);

	public List<User> findByLastName(String lastName);

	public List<User> findByFirstNameOrLastName(String firstName, String lastName);

	public List<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

	public User findByUsername(String username);
	
	public User findByUsernameAndPassword(String username, String password);

}

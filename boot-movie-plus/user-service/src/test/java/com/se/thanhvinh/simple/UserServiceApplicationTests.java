package com.se.thanhvinh.simple;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.se.thanhvinh.simple.domain.entity.User;
import com.se.thanhvinh.simple.domain.repository.UserRepository;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
		User user = new User();
		user.setEmail("email");
		user.setImageUrl("imageUrl");
		user.setName("name");
		user.setPassword("password");
		
		User user1 = new User();
		user1.setEmail("email 1");
		user1.setImageUrl("imageUrl 1");
		user1.setName("name 1");
		user1.setPassword("password 1");
		
		User user2 = new User();
		user2.setEmail("email 2");
		user2.setImageUrl("imageUrl 2");
		user2.setName("name 2");
		user2.setPassword("password 2");
		
		List<User> users = new ArrayList<>();
		users.add(user2);
		users.add(user);
		users.add(user1);
		
		userRepository.saveAll(users);
	}

}

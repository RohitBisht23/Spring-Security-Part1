package com.RohitBisht.SpringSecurity.SpringSecurity.Learning;

import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Entity.UserEntity;
import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityLearningApplicationTests {


	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		UserEntity user = new UserEntity(4L, "rohit@gmail.com", "1234");
		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);
		System.out.println(id);

	}

}

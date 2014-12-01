package com.service.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.User;
import com.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserServiceTest {
	
	private static final Logger logger = Logger.getLogger(UserServiceTest.class);
	@Autowired
	private UserService userService;
	
	@Test
	public void deleteTest(){
		User user = this.userService.getUser(1100);
		this.userService.deleteUser(user);
	}
	

}

package com.service.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.User;
import com.service.PriceService;
import com.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PriceServiceTest {
	
	private static final Logger logger = Logger.getLogger(PriceServiceTest.class);
	@Autowired
	private PriceService p;
	@Test
	public void getPrice(){
	
		logger.info(this.p.getPrice().getMaxprice());
	}
	
	
	

}

package com.service.test;


import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.common.Page;
import com.entity.TransferRecord;
import com.entity.Wallet;
import com.service.WalletService;
import com.utils.IDGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class WalletServiceTest {

	@Autowired
	private WalletService walletService;
	private static final Logger logger = Logger.getLogger(WalletServiceTest.class);

	@Test
	public void testAdd(){
		int total = this.walletService.getTotalNum();
		
		Wallet wallet = new Wallet();
		wallet.setAddress(IDGenerator.generatorIdHex());
		wallet.setAmount(0.5);
		wallet.setLocked("false");
		//userId
		this.walletService.addWallet(1150);
		
		int newTotal = this.walletService.getTotalNum();
		
		Assert.assertEquals(total+1, newTotal);
		
	}
	
	@Test
	public void testServiceFind(){
		
		Page page=new Page();
		List<Wallet> list = this.walletService.findList(page,1100);
		
		for(Wallet w:list){
			logger.info(w.getAddress()+"  "+w.getAmount()+"  "+w.getLocked());
		}
		
	}
	
	
	@Test
	public void testGet(){
		logger.info(this.walletService.getWallet("547444c94ab55bdf").getLocked());
	}
	
	@Test
	public void testTotalNum(){
		logger.info(this.walletService.getTotalNum());
	}
	
	@Test
	public void testDelete(){
		int total = this.walletService.getTotalNum();
		
		this.walletService.deleteWallet("547444c94ab55bdf");
		
		int newTotal = this.walletService.getTotalNum();
		
		Assert.assertEquals(total-1, newTotal);
	}
	
	
}

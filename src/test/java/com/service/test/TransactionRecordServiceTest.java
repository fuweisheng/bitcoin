package com.service.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.common.Page;
import com.dao.TransferRecordDao;
import com.entity.TransferRecord;
import com.service.TransferRecordService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TransactionRecordServiceTest {
	
	@Autowired
	private TransferRecordService transactionRecordService;
	@Autowired
	private TransferRecordDao transferRecordDao;

	private static final Logger logger = Logger.getLogger(TransactionRecordServiceTest.class);

	@Test
	public void testServiceAdd(){

		int total = this.transactionRecordService.getTotalNum();
		
		TransferRecord transferRecord = new TransferRecord();
		transferRecord.setTransferAddress("55555");
		transferRecord.setDestinationAddress("88888");
		transferRecord.setTransferType("汇入");
		transferRecord.setTransferAmount(0.5);
		transferRecord.setTransferState("失败");
		transferRecord.setTransferDate(new Date());
		
		this.transactionRecordService.addTransfer(transferRecord,1150);
		
		int newTotal = this.transactionRecordService.getTotalNum();
		
		Assert.assertEquals(total+1, newTotal);
	}
	
	@Test
	public void testServiceFind(){
		
		Page page=new Page();
		List<TransferRecord> list = this.transactionRecordService.findList(page,1100);
		for(TransferRecord t:list){
			logger.info(t.getId()+"  "+t.getDestinationAddress()+"  "+t.getTransferType()+"  "
					+t.getTransferAmount()+"  "+t.getTransferState()+"  "+
					t.getTransferDate()+"  "+t.getTransferAddress());
		}
		
	}
	
	
	@Test
	public void testGet(){
		System.out.println(this.transactionRecordService.getTransfer(50).getTransferState());
	}
	
	@Test
	public void testTotalNum(){
		System.out.println(this.transactionRecordService.getTotalNum());
	}
	
	@Test
	public void testDelete(){
		TransferRecord entity = this.transactionRecordService.getTransfer(4250);
		this.transactionRecordService.deleteTransfer(entity);
	}
	
	@Test
	public void testFindWithCondition(){
		List<TransferRecord> list = this.transferRecordDao.findWithCondition();
		for(TransferRecord t:list){
			logger.info(t.getId()+"  "+t.getDestinationAddress()+"  "+t.getTransferType()+"  "
					+t.getTransferAmount()+"  "+t.getTransferState()+"  "+
					t.getTransferDate()+"  "+t.getTransferAddress());
		}
	}
	
	
}

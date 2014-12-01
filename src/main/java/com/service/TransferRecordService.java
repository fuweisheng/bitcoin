package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.Page;
import com.dao.TransferRecordDao;
import com.dao.UserDao;
import com.dao.WalletDao;
import com.entity.TransferRecord;
import com.entity.User;
import com.entity.Wallet;

@Transactional
@Service
public class TransferRecordService {

	@Autowired
	private TransferRecordDao transactionRecordDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private WalletDao walletDao;
	
	public List<TransferRecord> findList(Page page,int userId){
		int currentPage = page.getCurrentPage();
		int direction = page.getDirection();
		String con1 = page.getCon1();
		String con2 = page.getCon2();
		if(con1 != null && con2 !=null){
			if(!"全部".equals(con1)){
				con1 = " transfer_type='"+con1+"' ";
			}else{
				con1 = null;
			}
			if(!"全部".equals(con2)){
				con2 = " transfer_state='"+con2+"' ";
			}else {
				con2 = null;
			}
		}
		return this.transactionRecordDao.findListByCondition(currentPage, direction, con1, con2,page,userId);
	}
	
	public void addTransfer(TransferRecord transfer,int userId){
		//获取转转账的钱包，扣除相应的数量
		Wallet wallet = this.walletDao.get(transfer.getTransferAddress());
		double amount = wallet.getAmount();
		amount = amount - transfer.getTransferAmount();
		wallet.setAmount(amount);
		this.walletDao.save(wallet);
		//增加交易记录事务
		transfer.setTransferType("汇出");
		transfer.setTransferState("等待");
		transfer.setTransferDate(new Date());
		User user = this.userDao.get(userId);
		transfer.setUser(user);
		this.transactionRecordDao.addTransferRecord(transfer);
	}
	
	@Scheduled(fixedDelay=60000)
	public void executeTransfer(){
		
		List<TransferRecord> list =this.transactionRecordDao.findWithCondition();
		if(list == null)
			return ;

		TransferRecord newTransferRecord;
		User desUser;
		Wallet desWallet;
		for(TransferRecord t : list){
			//根据目标地址，获取目标钱包
			desWallet = this.walletDao.get(t.getDestinationAddress());
			//根据目标地址，获取目标User
			desUser =desWallet.getUser();
			//添加新的交易记录
			newTransferRecord = new TransferRecord();
			newTransferRecord.setDestinationAddress(t.getTransferAddress());
			newTransferRecord.setTransferAddress(t.getDestinationAddress());
			newTransferRecord.setTransferAmount(t.getTransferAmount());
			newTransferRecord.setTransferType("汇入");
			newTransferRecord.setTransferState("完成");
			newTransferRecord.setTransferDate(new Date());
			newTransferRecord.setUser(desUser);
			this.transactionRecordDao.save(newTransferRecord);
			//目标钱包增加金额
			double totalAmount  = desWallet.getAmount()+t.getTransferAmount();
			desWallet.setAmount(totalAmount);
			this.walletDao.saveOrUpdate(desWallet);
			//将原来的交易状态改为完成
			t.setTransferState("完成");
			this.transactionRecordDao.saveOrUpdate(t);
		}
		System.out.println("------------------------------------");
		System.out.println("--------------调度完成-----------------");
		System.out.println("------------------------------------");
		
	}
	
	public TransferRecord getTransfer(int id){
		return this.transactionRecordDao.get(id);
	}
	
	public void deleteTransfer(TransferRecord entity){
		this.transactionRecordDao.deleteTransferRecord(entity);
	}
	
	public int getTotalNum(){
		return this.transactionRecordDao.getTotalNum();
	}
	
}

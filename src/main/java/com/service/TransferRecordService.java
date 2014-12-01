package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

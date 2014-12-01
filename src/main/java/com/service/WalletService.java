package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.Page;
import com.dao.UserDao;
import com.dao.WalletDao;
import com.entity.User;
import com.entity.Wallet;
import com.utils.IDGenerator;

@Transactional
@Service
public class WalletService {
	
	@Autowired
	private WalletDao walletDao;
	@Autowired
	private UserDao userDao;
	
	public List<Wallet> findList(Page page,int userId){
		int currentPage = page.getCurrentPage();
		int direction = page.getDirection();
		String con1 = page.getCon1();
		String con2 = page.getCon2();
		if(con1 != null && con2 !=null){
			if(!"全部".equals(con1)){
				con1 = " address like '%"+con1+"%' ";
			}else{
				con1 = null;
			}
			if(!"全部".equals(con2)){
				con2 = " locked='"+con2+"' ";
			}else {
				con2 = null;
			}
		}
		return this.walletDao.findListByCondition(currentPage, direction, con1, con2,page,userId);
		
	}
	
	public void updateLocked(String locked,String address){
		Wallet wallet = this.getWallet(address);
		if(wallet == null)
			return ;
		if(locked.equalsIgnoreCase("true"))
			wallet.setLocked("false");
		else if(locked.equalsIgnoreCase("false"))
			wallet.setLocked("true");
		else
			wallet.setLocked("true");
		this.walletDao.saveOrUpdate(wallet);
	}
	
	public void addWallet(int userId){
		User user = this.userDao.get(userId);
		Wallet wallet = new Wallet();
		wallet.setAddress(IDGenerator.generatorIdHex());
		wallet.setAmount(0.0);
		wallet.setLocked("false");
		wallet.setUser(user);
		this.walletDao.addWallet(wallet);
	}
	
	public void deleteWallet(String address){
		Wallet wallet = this.walletDao.get(address);
		if(wallet == null)
			return ;
		this.walletDao.delete(wallet);
	}
	
	public int getTotalNum(){
		return this.walletDao.getTotalNum();
	}
	
	public Wallet getWallet(String address){
		return this.walletDao.get(address);
	}
	

}

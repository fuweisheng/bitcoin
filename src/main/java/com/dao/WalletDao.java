package com.dao;

import org.springframework.stereotype.Repository;

import com.common.PageDao;
import com.entity.Wallet;

@Repository
public class WalletDao extends PageDao<Wallet, String> {

	public void addWallet(Wallet entity){
		super.save(entity);
	}
	
	public void deleteWallet(Wallet entity){
		super.delete(entity);
	}
	
}

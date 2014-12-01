package com.dao;

import org.springframework.stereotype.Repository;

import com.common.PageDao;
import com.entity.TransferRecord;

@Repository
public class TransferRecordDao extends PageDao<TransferRecord, Integer> {
	
	public void addTransferRecord(TransferRecord entity){
		super.save(entity);
	}
	
	public void deleteTransferRecord(TransferRecord entity){
		super.delete(entity);
	}
	
}

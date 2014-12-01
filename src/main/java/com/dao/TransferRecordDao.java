package com.dao;

import java.util.List;

import org.hibernate.Query;
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
	
	public List<TransferRecord> findWithCondition(){
		String sql = "from "+super.getEntity()+" where transfer_state = '等待'";
		return super.getHibernateTemplate().find(sql);
		
	}
	
}

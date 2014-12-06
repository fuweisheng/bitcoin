package com.dao;

import org.springframework.stereotype.Repository;

import com.common.PageDao;
import com.entity.Price;

@Repository
public class PriceDao extends PageDao<Price, Integer> {
	/**
	 * 保存获取的比特币价格到数据库
	 * @param entity
	 */
	public void addPrice(Price entity){
		super.save(entity);
	}
	/**
	 * 获取最新比特币的id
	 * @return
	 */
	public int getPriceId(){
		
		String sql = "select id from(select * from tb_price order by id desc)where rownum<2";
		
		return super.getJdbcTemplate().queryForInt(sql);
		
	}
	
	public Price getPrice(){
		int id = this.getPriceId();
		return super.get(id);
	}

}

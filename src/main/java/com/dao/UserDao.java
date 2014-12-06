package com.dao;

import org.springframework.stereotype.Repository;

import com.common.BaseDao;
import com.entity.User;

@Repository
public class UserDao extends BaseDao<User, Integer> {
	
	public Integer getId(String email){
		String sql = "select id from tb_user1 where email='"+email+"'";
		return super.getJdbcTemplate().queryForInt(sql);
	}

}

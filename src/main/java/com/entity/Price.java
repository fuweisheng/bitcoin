package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 比特币价格
 * @author chenlixin
 *
 */
@Entity
@Table(name = "tb_price")
public class Price {
	private int id;
	private String price;
	private String maxprice;
	private String minprice;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TASK_SEQ")
	@SequenceGenerator(name="TASK_SEQ",sequenceName="SEQ_USER_TASK")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name="maxprice")
	public String getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}
	@Column(name="minprice")
	public String getMinprice() {
		return minprice;
	}
	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}
	
	
	



}

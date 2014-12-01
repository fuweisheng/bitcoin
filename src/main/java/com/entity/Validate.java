package com.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_validate")
public class Validate {
	
	private int id;
	private String vcode;
	private Date registerDate;
	private int state; // 0->未验证 1->已验证
	private User user;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TEMP_SEQ")
	@SequenceGenerator(name="TEMP_SEQ",sequenceName="SEQ_TEMP_TASK")
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="vcode")
	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	
	@Column(name="register_date")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name="state")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",unique=true)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

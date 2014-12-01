package com.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "tb_user1")
public class User {
	private int id;
	private String email;
	private String password;
	private String name;
	private Validate validate;
	private Set<Wallet> wallets;
	private Set<TransferRecord> transferRecords;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TASK_SEQ")
	@SequenceGenerator(name="TASK_SEQ",sequenceName="SEQ_USER_TASK")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	public Validate getValidate() {
		return validate;
	}

	public void setValidate(Validate validate) {
		this.validate = validate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="user")
	public Set<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(Set<Wallet> wallets) {
		this.wallets = wallets;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="user")
	public Set<TransferRecord> getTransferRecords() {
		return transferRecords;
	}

	public void setTransferRecords(Set<TransferRecord> transferRecords) {
		this.transferRecords = transferRecords;
	}

}

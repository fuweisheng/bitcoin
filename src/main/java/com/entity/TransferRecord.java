package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_transfer_record")
public class TransferRecord {

	private int id;
	private String transferAddress; // 交易的来源
	private String destinationAddress; // 交易的去向
	private String transferType;
	private double transferAmount;
	private String transferState;
	private Date transferDate;
	private User user;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TEMP_SEQ")
	@SequenceGenerator(name="TEMP_SEQ",sequenceName="SEQ_TEMP_TASK")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "transfer_address")
	public String getTransferAddress() {
		return transferAddress;
	}

	public void setTransferAddress(String transferAddress) {
		this.transferAddress = transferAddress;
	}

	@Column(name = "destination_address")
	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	@Column(name = "transfer_type")
	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	@Column(name = "transfer_amount",precision=8)
	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	@Column(name = "transfer_state")
	public String getTransferState() {
		return transferState;
	}

	public void setTransferState(String transferState) {
		this.transferState = transferState;
	}

	@Column(name = "transfer_date")
	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

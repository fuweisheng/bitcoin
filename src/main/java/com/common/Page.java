package com.common;

public class Page {
	private int currentPage;
	private int totalPage;
	private int direction;
	private String con1="全部";
	private String con2="全部";
	
	public Page() {
		super();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getCon1() {
		return con1;
	}

	public void setCon1(String con1) {
		if(con1 == null)
			con1 = "全部";
		this.con1 = con1;
	}

	public String getCon2() {
		return con2;
	}

	public void setCon2(String con2) {
		if(con2 == null)
			con2="全部";
		this.con2 = con2;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}

package com.annunzio.web;

public class Company {
	private int id;
	private String companyName;
	private String ceo;
	private double stockPrice;
	private String headquarters;
	
	
	public Company(int id, String companyName, String ceo, double stockPrice, String headquarters) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.ceo = ceo;
		this.stockPrice = stockPrice;
		this.headquarters = headquarters;
	}
	public Company(int id, String companyName, String ceo, String headquarters) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.ceo = ceo;
		this.headquarters = headquarters;
	}
	
	public Company(String companyName, String ceo, String headquarters) {
		super();
		this.companyName = companyName;
		this.ceo = ceo;
		this.headquarters = headquarters;
	}
	
	public Company(String companyName) {
		super();
		this.companyName = companyName;
	}
	
	public Company(int id, String companyName) {
		super();
		this.id = id;
		this.companyName = companyName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public String getHeadquarters() {
		return headquarters;
	}
	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", ceo=" + ceo + ", stockPrice=" + stockPrice
				+ ", headquarters=" + headquarters + "]";
	}

}

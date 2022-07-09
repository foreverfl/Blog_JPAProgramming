package com.example.ObjectOrientedQueryLanguage_01_JPQL.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private long id;

	private String name;
	private int price;
	private int StockAmount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockAmount() {
		return StockAmount;
	}

	public void setStockAmount(int stockAmount) {
		StockAmount = stockAmount;
	}

}

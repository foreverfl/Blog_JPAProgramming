package com.example._AdvancedMapping_04_MappedSuperclass.entities;

import javax.persistence.Entity;

@Entity
public class Seller extends BaseEntity {
	private String shopName;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}

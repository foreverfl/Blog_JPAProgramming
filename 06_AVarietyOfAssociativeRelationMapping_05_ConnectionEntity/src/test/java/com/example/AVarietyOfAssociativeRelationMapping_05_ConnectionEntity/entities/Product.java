package com.example.AVarietyOfAssociativeRelationMapping_05_ConnectionEntity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@Column(name = "PRODUCT_ID")
	private String id;

	private String name; // 연관관계의 필요성이 없기 때문에 단방향으로 만들어졌다.

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

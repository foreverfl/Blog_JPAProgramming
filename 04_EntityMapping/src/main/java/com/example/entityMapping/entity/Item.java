package com.example.entityMapping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // 해당 클래스가 엔티티로 쓰일 것임을 알려준다.
public class Item {

	@Id // 해당 칼럼을 기본 키로 설정한다.
	@GeneratedValue // 해당 칼럼을 자동 생성된 값으로 채운다.
	@Column(name = "ITEM_ID") // 해당 칼럼에 칼럼명을 설정한다.
	private Long id;

	private String name;
	private int price; // 자바 기본형 변수를 쓰고 칼럼명을 설정하지 않으면 not null로 설정된다.
	private int stockQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
		return "Item{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
	}
}

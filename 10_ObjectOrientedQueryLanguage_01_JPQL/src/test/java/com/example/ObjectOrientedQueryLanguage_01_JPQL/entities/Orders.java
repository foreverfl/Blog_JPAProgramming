package com.example.ObjectOrientedQueryLanguage_01_JPQL.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.ObjectOrientedQueryLanguage_01_JPQL.values.Address;

@Entity
public class Orders {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private long id;

	private int orderAmount;

	@Embedded
	Address address;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID") // 외래키(양방향)
	private Member member;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID") // 외래키(단방향)
	private Product product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}

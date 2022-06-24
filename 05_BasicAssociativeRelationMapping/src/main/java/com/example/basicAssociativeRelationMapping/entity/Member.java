package com.example.basicAssociativeRelationMapping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	private String name;

	private String city;
	private String street;
	private String zipcode;

	@OneToMany(mappedBy = "member") // Order 클래스와의 단방향 연관관계를 나타낸다. member는 Order클래스의 필드값이다.
	private List<Order> orders = new ArrayList<Order>();
	// 하나에서 여러 개의 값을 가져오려면 Collections가 쓰여야 한다.
	// Order 클래스 -> Member 클래스 (단방향 연관관계)
	// Member 클래스 -> Order 클래스 (단방향 연관관계)
	// Order 클래스와 Member 클래스는 양방향 연관관계를 가진다.

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}

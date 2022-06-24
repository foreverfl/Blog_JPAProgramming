package com.example.basicAssociativeRelationMapping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;

	@ManyToOne // Item 클래스와의 단방향 연관관계를 나타낸다.
	@JoinColumn(name = "ITEM_ID") // 외래키는 ITEM_ID이다.
	private Item item;
	// Item 클래스에는 연관관계가 나타나 있지 않기 때문에 단방향 연관관계이다.

	@ManyToOne // Order 클래스와의 단방향 연관관계를 나타낸다.
	@JoinColumn(name = "ORDER_ID") // 외래키는 ORDER_ID이다.
	private Order order;
	// Order 클래스 -> OrderItem 클래스 (단방향 연관관계)
	// OrderItem 클래스 -> Order 클래스 (단방향 연관관계)
	// Order 클래스와 OrderItem 클래스는 양방향 연관관계를 가진다.

	private int orderPrice;
	private int count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrderItem{" + "id=" + id + ", buyPrice=" + orderPrice + ", count=" + count + '}';
	}
}

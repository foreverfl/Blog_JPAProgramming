package com.example.basicAssociativeRelationMapping.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@ManyToOne // Member 클래스와의 단방향 연관관계를 나타낸다.
	@JoinColumn(name = "MEMBER_ID") // 외래키는 MEMBER_ID이다.
	private Member member;
	// Order 클래스 -> Member 클래스 (단방향 연관관계)
	// Member 클래스 -> Order 클래스 (단방향 연관관계)
	// Order 클래스와 Member 클래스는 양방향 연관관계를 가진다.

	@OneToMany(mappedBy = "order") // OrderItem과의 단방향 연관관계를 나타낸다. order는 OrderItem 클래스의 필드값이다.
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	// Order 클래스 -> OrderItem 클래스 (단방향 연관관계)
	// OrderItem 클래스 -> Order 클래스 (단방향 연관관계)
	// Order 클래스와 OrderItem 클래스는 양방향 연관관계를 가진다.

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public void setMember(Member member) {
		if (this.member != null) {
			this.member.getOrders().remove(this);
		}
		this.member = member;
		member.getOrders().add(this);
	}
	//

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", status=" + status + '}';
	}
}

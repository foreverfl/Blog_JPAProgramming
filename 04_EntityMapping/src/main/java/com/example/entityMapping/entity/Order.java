package com.example.entityMapping.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDERS") // 해당 테이블의 이름을 설정한다.
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "MEMBER_ID")
	private Long memberId;

	@Temporal(TemporalType.TIMESTAMP) // 해당 칼럼에 날짜 타입을 매핑한다.
	private Date orderDate;

	@Enumerated(EnumType.STRING) // 해당 칼럼에 enum 타입을 매핑한다.
	private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
}

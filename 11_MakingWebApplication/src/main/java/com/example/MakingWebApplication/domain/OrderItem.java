package com.example.MakingWebApplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.MakingWebApplication.domain.item.Item;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID") // 외래키를 소유한다.
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID") // 외래키를 소유한다.
	private Order order;

	private int orderPrice;
	private int count;

	// 생성 메서드
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {

		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);

		item.removeStock(count);
		return orderItem;
	}

	// 주문 취소
	public void cancel() {
		getItem().addStock(count);
	}

	// 주문상품 전체 가격 조회
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}

	// getter, setter
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

	public void setOrderPrice(int buyPrice) {
		this.orderPrice = buyPrice;
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

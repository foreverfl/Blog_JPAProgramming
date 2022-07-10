package com.example.SpringDataJPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringDataJPA.domain.Delivery;
import com.example.SpringDataJPA.domain.Member;
import com.example.SpringDataJPA.domain.Order;
import com.example.SpringDataJPA.domain.OrderItem;
import com.example.SpringDataJPA.domain.OrderSearch;
import com.example.SpringDataJPA.domain.item.Item;
import com.example.SpringDataJPA.repository.MemberRepository;
import com.example.SpringDataJPA.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ItemService itemService;

	/**
	 * 주문
	 */
	public Long order(Long memberId, Long itemId, int count) {

		// 엔티티 조회
		Member member = memberRepository.findById(memberId).get();
		Item item = itemService.findOne(itemId);

		// 배송정보 생성
		Delivery delivery = new Delivery(member.getAddress());
		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		// 주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);

		// 주문 저장
		orderRepository.save(order);
		return order.getId();
	}

	/**
	 * 주문 취소
	 */
	public void cancelOrder(Long orderId) {

		// 주문 엔티티 조회
		Order order = orderRepository.findById(orderId).get();

		// 주문 취소
		order.cancel();
	}

	/**
	 * 주문 검색
	 */
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch.toSpecification()); // Specification 사용
		// return orderRepository.search(orderSearch); //QueryDSL 사용
	}

}

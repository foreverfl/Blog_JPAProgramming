package com.example.SpringDataJPA.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.example.SpringDataJPA.domain.Order;
import com.example.SpringDataJPA.domain.OrderSearch;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

	private final JPAQueryFactory queryFactory;

	public OrderRepositoryImpl(JPAQueryFactory queryFactory) {
		super(Order.class);
		this.queryFactory = queryFactory;
	}

	@Override
	public List<Order> search(OrderSearch orderSearch) {
		QOrder order = QOrder.order;
		QMember member = QMember.member;

		JPQLQuery query = from(order);

		if (StringUtils.hasText(orderSearch.getMemberName())) {
			query.leftJoin(order.member, member).where(member.name.contains(orderSearch.getMemberName()));
		}

		if (orderSearch.getOrderStatus() != null) {
			query.where(order.status.eq(orderSearch.getOrderStatus()));
		}

		return query.list(order);
	}
}

package com.example.MakingWebApplication.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.MakingWebApplication.domain.Member;
import com.example.MakingWebApplication.domain.OrderSearch;

@Repository
public class OrderRepository {

	@PersistenceContext
	EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	public List<Order> findAll(OrderSearch orderSearch) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> o = cq.from(Order.class);

		List<Predicate> criteria = new ArrayList<Predicate>();

		// 주문 상태 검색
		if (orderSearch.getOrderStatus() != null) {
			Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
			criteria.add(status);
		}
		// 회원 이름 검색
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			Join<Order, Member> m = o.join("member", JoinType.INNER); // 회원과 조인
			Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
			criteria.add(name);
		}

		cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
		TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); // 최대 검색 1000 건으로 제한
		return query.getResultList();
	}
}
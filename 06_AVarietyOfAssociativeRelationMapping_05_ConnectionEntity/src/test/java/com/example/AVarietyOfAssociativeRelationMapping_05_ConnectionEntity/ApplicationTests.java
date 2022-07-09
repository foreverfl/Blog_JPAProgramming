package com.example.AVarietyOfAssociativeRelationMapping_05_ConnectionEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.AVarietyOfAssociativeRelationMapping_05_ConnectionEntity.entities.Member;
import com.example.AVarietyOfAssociativeRelationMapping_05_ConnectionEntity.entities.OrdertoUse;
import com.example.AVarietyOfAssociativeRelationMapping_05_ConnectionEntity.entities.Product;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Commit
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Order(1)
	@Transactional
	public void save() {
		// 회원 저장
		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);

		// 상품 저장
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품A");
		em.persist(productA);

		// 회원상품 저장
		OrdertoUse order = new OrdertoUse();
		order.setMember(member1); // 주문 회원 - 연관관계 설정
		order.setProduct(productA); // 주문 상품 - 연관관계 설정
		order.setOrderAmount(2); // 주문 수량
		em.persist(order);
	}

	@Test
	@Order(2)
	@Transactional
	public void find() {

		Long orderId = 1L;
		OrdertoUse order = em.find(OrdertoUse.class, orderId);
		Member member = order.getMember();
		Product product = order.getProduct();

		System.out.println("member= " + member.getUsername());
		System.out.println("product= " + product.getName());
		System.out.println("orderAmount= " + order.getOrderAmount());

	}

}
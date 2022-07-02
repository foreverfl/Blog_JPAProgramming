package com.example.AVarietyOfAssociativeRelationMapping_04_ManyToMany;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.AVarietyOfAssociativeRelationMapping_04_ManyToMany.entities.Member;
import com.example.AVarietyOfAssociativeRelationMapping_04_ManyToMany.entities.Product;

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
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품A");
		em.persist(productA);

		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.getProducts().add(productA); // 연관관계 설정
		em.persist(member1);
	}

	@Test
	@Order(2)
	@Transactional
	public void find() {
		Member member = em.find(Member.class, "member1");
		List<Product> products = member.getProducts(); // 객체 그래프 탐색
		for (Product product : products) {
			System.out.println("product.name= " + product.getName());
		}
	}

	@Test
	@Order(3)
	@Transactional
	public void findInverse() {
		Product product = em.find(Product.class, "productA");
		List<Member> members = product.getMembers();
		for (Member member : members) {
			System.out.println("member= " + member.getUsername());
		}
	}

}
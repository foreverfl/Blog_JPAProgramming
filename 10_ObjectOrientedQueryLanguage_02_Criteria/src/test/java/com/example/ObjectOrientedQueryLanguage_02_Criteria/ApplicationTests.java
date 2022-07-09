package com.example.ObjectOrientedQueryLanguage_02_Criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.ObjectOrientedQueryLanguage_02_Criteria.entities.Member;
import com.example.ObjectOrientedQueryLanguage_02_Criteria.entities.Orders;
import com.example.ObjectOrientedQueryLanguage_02_Criteria.entities.Product;
import com.example.ObjectOrientedQueryLanguage_02_Criteria.entities.Team;
import com.example.ObjectOrientedQueryLanguage_02_Criteria.values.Address;

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
		Team team = new Team();
		team.setName("팀1");

		List<Member> members = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Member member = new Member();
			member.setUsername("회원" + Integer.toString(i));
			member.setAge(i);
			member.setTeam(team);
			members.add(member);
			em.persist(member);
		}

		List<Product> products = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Product product = new Product();
			product.setName("물건" + Integer.toString(i));
			product.setPrice(i * 100);
			product.setStockAmount(i);

			products.add(product);
			em.persist(product);
		}

		for (int i = 1; i <= 10; i++) {
			Orders order = new Orders();
			order.setAddress(new Address("서울", "새창로길", "1234"));
			order.setOrderAmount(i);
			order.setMember(members.get(i - 1));
			order.setProduct(products.get(i - 1));
			em.persist(order);
		}

	}

	@Test
	@Order(2)
	@Transactional
	public void basicGrammar() {
		System.out.println("=== 1. 기본 문법 ===");
		System.out.println("=== (1) 기본 ===");
		// JPQL: SELECT m FROM Member m
		CriteriaBuilder cb_01 = em.getCriteriaBuilder(); // Criteria 쿼리 빌더
		CriteriaQuery<Member> cq_01 = cb_01.createQuery(Member.class); // 생성/반환 타입 지정
		Root<Member> m_01 = cq_01.from(Member.class); // FROM 절
		cq_01.select(m_01); // SELECT 절

		TypedQuery<Member> query_01 = em.createQuery(cq_01);
		List<Member> resultList_01 = query_01.getResultList();
		for (Member member : resultList_01) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (2) 검색 조건 추가 ===");
		// JPQL:
		// SELECT m FROM Member m
		// WHERE m.username='회원1'
		// ORDER BY m.age DESC
		CriteriaBuilder cb_02 = em.getCriteriaBuilder();
		CriteriaQuery<Member> cq_02 = cb_02.createQuery(Member.class);
		Root<Member> m_02 = cq_02.from(Member.class);

		// 검색 조건 정의
		Predicate usernameEqual = cb_02.equal(m_02.get("username"), "회원1");

		// 정렬 조건 정의
		javax.persistence.criteria.Order ageDesc = cb_02.desc(m_02.get("age"));

		// 쿼리 생성
		cq_02.select(m_02).where(usernameEqual).orderBy(ageDesc);

		TypedQuery<Member> query_02 = em.createQuery(cq_02);
		List<Member> resultList_02 = query_02.getResultList();
		for (Member member : resultList_02) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (3) 숫자 타입 검색 ===");

	}

	@Test
	@Order(3)
	@Transactional
	public void createCriteriaQuery() {
		System.out.println("=== 2. 쿼리 생성 ===");
		System.out.println("=== (1) 기본 ===");

	}

	@Test
	@Order(4)
	@Transactional
	public void select() {
		System.out.println("=== 3. 조회 ===");
		System.out.println("=== (1) 한 건 ===");
		System.out.println("=== (2) 여러 건 ===");
		System.out.println("=== (3) DISTINCT ===");
		System.out.println("=== (4) NEW, contstruct() ===");
		System.out.println("=== (5) 튜플 ===");

	}

	@Test
	@Order(5)
	@Transactional
	public void set() {
		System.out.println("=== 4. 집합 ===");
		System.out.println("=== (1) GROUP BY ===");
		System.out.println("=== (2) HAVING ===");
		System.out.println("=== (3) 정렬 ===");

	}

	@Test
	@Order(6)
	@Transactional
	public void join() {
		System.out.println("=== 5. 조인 ===");

	}

	@Test
	@Order(7)
	@Transactional
	public void subQuery() {
		System.out.println("=== 6. 서브 쿼리 ===");
		System.out.println("=== (1) GROUP BY ===");
		System.out.println("=== (2) HAVING ===");
		System.out.println("=== (3) 정렬 ===");

	}

	@Test
	@Order(8)
	@Transactional
	public void in() {
		System.out.println("=== 7. IN ===");

	}

	@Test
	@Order(9)
	@Transactional
	public void usingCase() {
		System.out.println("=== 8. CASE ===");

	}

	@Test
	@Order(10)
	@Transactional
	public void defineParameter() {
		System.out.println("=== 9. 파라미터 정의 ===");

	}

	@Test
	@Order(11)
	@Transactional
	public void callNativeFunction() {
		System.out.println("=== 10. 네이티브 함수 호출 ===");

	}

	@Test
	@Order(12)
	@Transactional
	public void dynamicQuery() {
		System.out.println("=== 11. 동적 쿼리 ===");

	}

}
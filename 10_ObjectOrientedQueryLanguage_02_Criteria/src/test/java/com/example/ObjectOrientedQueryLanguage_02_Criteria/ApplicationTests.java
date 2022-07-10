package com.example.ObjectOrientedQueryLanguage_02_Criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.ObjectOrientedQueryLanguage_02_Criteria.DTO.UserDTO;
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
		// SELECT m FROM Member m
		// WHERE m.age > 5 ORDER BY m.age DESC
		CriteriaBuilder cb_03 = em.getCriteriaBuilder();
		CriteriaQuery<Member> cq_03 = cb_03.createQuery(Member.class);
		Root<Member> m_03 = cq_03.from(Member.class);

		Predicate ageGt = cb_03.greaterThan(m_03.<Integer>get("age"), 5);

		cq_03.select(m_03);
		cq_03.where(ageGt);
		cq_03.orderBy(cb_03.desc(m_03.get("age")));

		TypedQuery<Member> query_03 = em.createQuery(cq_03);
		List<Member> resultList_03 = query_03.getResultList();
		for (Member member : resultList_03) {
			System.out.println("member: " + member);
		}
	}

	@Test
	@Order(3)
	@Transactional
	public void select() {
		System.out.println("=== 2. 조회 ===");
		System.out.println("=== (1) 한 건 ===");
		System.out.println("1-(1)을 참조합니다.");
		System.out.println("=== (2) 여러 건 / DISTINCT ===");
		// JPQL: SELECT DISTINCT m.username, m.age
		CriteriaBuilder cb_02 = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq_02 = cb_02.createQuery(Object[].class);
		Root<Member> m_02 = cq_02.from(Member.class);

		cq_02.multiselect(m_02.get("username"), m_02.get("age")).distinct(true);

		TypedQuery<Object[]> query_02 = em.createQuery(cq_02);
		List<Object[]> resultList_02 = query_02.getResultList();
		for (Object[] row : resultList_02) {
			System.out.println("username: " + row[0] + " / " + "age: " + row[1]);
		}

		System.out.println("=== (3) NEW, contstruct() ===");
		// JPQL
		/* SELECT NEW com.example.ObjectOrientedQueryLanguage_02_Criteria.DTO.UserDTO(m.username, m.age) */
		// FROM Member m
		CriteriaBuilder cb_03 = em.getCriteriaBuilder();
		CriteriaQuery<UserDTO> cq_03 = cb_03.createQuery(UserDTO.class);
		Root<Member> m_03 = cq_03.from(Member.class);

		cq_03.select(cb_03.construct(UserDTO.class, m_03.get("username"), m_03.get("age")));

		TypedQuery<UserDTO> query_03 = em.createQuery(cq_03);
		List<UserDTO> resultList_03 = query_03.getResultList();
		for (UserDTO userDTO : resultList_03) {
			System.out.println("username: " + userDTO.getUsername() + " / " + "age: " + userDTO.getAge());
		}

		System.out.println("=== (4) 튜플 ===");
		// JPQL은 위와 같다.
		CriteriaBuilder cb_04 = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq_04 = cb_04.createQuery(Tuple.class);
		Root<Member> m_04 = cq_04.from(Member.class);

		cq_04.multiselect(m_04.get("username").alias("username"), m_04.get("age").alias("age")); // 튜플 별칭

		TypedQuery<Tuple> query_04 = em.createQuery(cq_04);
		List<Tuple> resultList_04 = query_04.getResultList();
		for (Tuple tuple : resultList_04) {
			String username = tuple.get("username", String.class);
			Integer age = tuple.get("age", Integer.class);
			System.out.println("username: " + username + " / " + "age: " + age);
		}
	}

	@Test
	@Order(4)
	@Transactional
	public void set() {
		System.out.println("=== 3. 집합 ===");
		System.out.println("=== (1) GROUP BY ===");
		// JPQL
		// SELECT m.team.name, MAX(m.age), MIN(m.age)
		// FROM Member m
		// GROUP BY m.team.name
		// 팀 이름별로 나이가 가장 많은 사람과 가장 적은 사람을 구한다.
		CriteriaBuilder cb_01 = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq_01 = cb_01.createQuery(Object[].class);
		Root<Member> m_01 = cq_01.from(Member.class);

		Expression maxAge = cb_01.max(m_01.<Integer>get("age"));
		Expression minAge = cb_01.min(m_01.<Integer>get("age"));

		cq_01.multiselect(m_01.get("team").get("name"), maxAge, minAge);
		cq_01.groupBy(m_01.get("team").get("name")); // GROUP BY

		TypedQuery<Object[]> query_01 = em.createQuery(cq_01);
		List<Object[]> resultList_01 = query_01.getResultList();
		for (Object[] row : resultList_01) {
			System.out.println("team: " + row[0] + " / " + "maxAge: " + row[1] + " / " + "maxAge: " + row[2]);
		}

		System.out.println("=== (2) HAVING ===");
		// 위 조건에서 최소 나이가 0 이상인 팀을 조회한다.
		CriteriaBuilder cb_02 = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq_02 = cb_02.createQuery(Object[].class);
		Root<Member> m_02 = cq_02.from(Member.class);

		cq_02.multiselect(m_02.get("team").get("name"), maxAge, minAge);
		cq_02.groupBy(m_02.get("team").get("name")); // GROUP BY
		cq_02.having(cb_02.gt(minAge, 0));

		TypedQuery<Object[]> query_02 = em.createQuery(cq_02);
		List<Object[]> resultList_02 = query_02.getResultList();
		for (Object[] row : resultList_02) {
			System.out.println("team: " + row[0] + " / " + "maxAge: " + row[1] + " / " + "maxAge: " + row[2]);
		}
	}

	@Test
	@Order(5)
	@Transactional
	public void join() {
		System.out.println("=== 4. 조인 ===");
	}

	@Test
	@Order(6)
	@Transactional
	public void subQuery() {
		System.out.println("=== 5. 서브 쿼리 ===");
	}

	@Test
	@Order(7)
	@Transactional
	public void in() {
		System.out.println("=== 6. IN ===");

	}

	@Test
	@Order(8)
	@Transactional
	public void usingCase() {
		System.out.println("=== 7. CASE ===");

	}

	@Test
	@Order(9)
	@Transactional
	public void defineParameter() {
		System.out.println("=== 8. 파라미터 정의 ===");

	}

}
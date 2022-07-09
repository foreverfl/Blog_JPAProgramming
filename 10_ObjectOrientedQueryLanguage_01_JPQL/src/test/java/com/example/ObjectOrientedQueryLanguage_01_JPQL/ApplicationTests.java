package com.example.ObjectOrientedQueryLanguage_01_JPQL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.ObjectOrientedQueryLanguage_01_JPQL.DTO.UserDTO;
import com.example.ObjectOrientedQueryLanguage_01_JPQL.entities.Member;
import com.example.ObjectOrientedQueryLanguage_01_JPQL.entities.Orders;
import com.example.ObjectOrientedQueryLanguage_01_JPQL.entities.Product;
import com.example.ObjectOrientedQueryLanguage_01_JPQL.entities.Team;
import com.example.ObjectOrientedQueryLanguage_01_JPQL.values.Address;

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
		System.out.println("=== (1) TypeQuery ===");
		TypedQuery<Member> query_01 = em.createQuery("SELECT m FROM Member m", Member.class);

		List<Member> resultList_01 = query_01.getResultList();
		for (Member member : resultList_01) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (2) Query ===");
		Query query_02 = em.createQuery("SELECt m.username, m.age from Member m");
		List resultList_02 = query_02.getResultList();
		for (Object o : resultList_02) {
			Object[] result = (Object[]) o;
			System.out.println("username: " + result[0] + " / " + "age: " + result[1]);
		}

	}

	@Test
	@Order(3)
	@Transactional
	public void parameterBinding() {
		System.out.println("=== 2. 파라미터 바인딩 ===");
		System.out.println("=== (1) 이름 기준 파라미터 ===");
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username =:username", Member.class);
		query.setParameter("username", "회원1");
		List<Member> resultList_01 = query.getResultList();
		for (Member member : resultList_01) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (2) 위치 기준 파라미터 ===");
		List<Member> resultList_02 = em.createQuery("SELECT m FROM Member m where m.username = ?1", Member.class) //
				.setParameter(1, "회원1") //
				.getResultList();
		for (Member member : resultList_02) {
			System.out.println("member: " + member);
		}
	}

	@Test
	@Order(4)
	@Transactional
	public void projection() {
		System.out.println("=== 3. 프로젝션 ===");
		System.out.println("=== (1) 엔티티 프로젝션 ===");
		System.out.println("'1. 기본문법'을 참조합니다.");
		System.out.println("=== (2) 임베디드 타입 프로젝션 ===");
		TypedQuery<Address> query_02 = em.createQuery("SELECT o.address FROM Orders o", Address.class);

		List<Address> resultList_02 = query_02.getResultList();
		for (Address address : resultList_02) {
			System.out.println("address: " + address);
		}

		System.out.println("=== (3) 스칼라 타입 프로젝션 ===");
		TypedQuery<String> query_03 = em.createQuery("SELECT DISTINCT username FROM Member m", String.class);
		// DISTINCT는 중복을 제거하는데 사용된다.

		List<String> resultList_03 = query_03.getResultList();
		for (String username : resultList_03) {
			System.out.println("username: " + username);
		}

		System.out.println("=== (4) 여러 값 조회 ===");
		Query query_04 = em.createQuery("SELECT m.username, m.age FROM Member m");
		// 여러 값을 조회하기 위해서는 TypedQuery가 아닌 Query를 사용해야 한다.

		List<Object[]> resultList_04 = query_04.getResultList();
		for (Object[] row : resultList_04) {
			String username = (String) row[0];
			Integer age = (Integer) row[1];
			System.out.println("username: " + username + " / " + "age: " + age);
		}

		System.out.println("=== (5) New 명령어 ===");
		// SELECT new 패키지명.클래스명(변수) FROM 테이블
		TypedQuery<UserDTO> query_05 = em.createQuery(
				"SELECT new com.example.ObjectOrientedQueryLanguage_01_JPQL.DTO.UserDTO(m.username, m.age) FROM Member m",
				UserDTO.class);

		List<UserDTO> resultList_05 = query_05.getResultList();
		for (UserDTO userDTO : resultList_05) {
			System.out.println(userDTO);
		}

	}

	@Test
	@Order(5)
	@Transactional
	public void paging() {
		System.out.println("=== 4. 페이징 API ===");
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC", Member.class);

		query.setFirstResult(6); // 7(0부터 시작한다)번부터 조회한다.
		query.setMaxResults(10); // 한 페이지에 최대 10개를 조회한다.
		List<Member> resultList = query.getResultList();
		for (Member member : resultList) {
			System.out.println(member);
		}
	}

	@Test
	@Order(6)
	@Transactional
	public void setAndAlignment() {
		System.out.println("=== 5. 집합과 정렬 ===");
		System.out.println("=== (1) 집합 함수 ===");
		Query query_01 = em
				.createQuery("SELECT COUNT(m), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) from Member m");

		Object[] result_01 = (Object[]) query_01.getSingleResult();
		System.out.println("COUNT: " + result_01[0]);
		System.out.println("SUM: " + result_01[1]);
		System.out.println("AVG: " + result_01[2]);
		System.out.println("MAX: " + result_01[3]);
		System.out.println("MIN: " + result_01[4]);

		System.out.println("=== (2) GROUP BY ===");
		Query query_02 = em.createQuery(
				"SELECT COUNT(m), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) from Member m LEFT JOIN m.team t GROUP BY t.name");
		// 팀 이름을 기준으로 그룹 별로 묶어서 통계 데이터를 구한다.
		// 해당 데이터에서는 팀을 하나만 설정했기 때문에 5-(1)과 같은 결과가 도출된다.
		Object[] result_02 = (Object[]) query_02.getSingleResult();
		System.out.println("COUNT: " + result_02[0]);
		System.out.println("SUM: " + result_02[1]);
		System.out.println("AVG: " + result_02[2]);
		System.out.println("MAX: " + result_02[3]);
		System.out.println("MIN: " + result_02[4]);

		System.out.println("=== (3) HAVING ===");
		Query query_03 = em.createQuery(
				"SELECT COUNT(m), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) from Member m LEFT JOIN m.team t GROUP BY t.name");
		// 5-(2) 데이터에서 평균 나이가 5 이상인 결과를 조회한다.
		// 해당 데이터에서는 팀을 하나만 설정했기 때문에 5-(1)과 같은 결과가 도출된다.
		Object[] result_03 = (Object[]) query_03.getSingleResult();
		System.out.println("COUNT: " + result_03[0]);
		System.out.println("SUM: " + result_03[1]);
		System.out.println("AVG: " + result_03[2]);
		System.out.println("MAX: " + result_03[3]);
		System.out.println("MIN: " + result_03[4]);

		System.out.println("=== (4) ORDER BY ===");
		TypedQuery<Member> query_04 = em.createQuery("SELECT m from Member m ORDER BY m.age DESC, m.username ASC",
				Member.class);
		// 나이를 기준으로 내림차순, 나이가 같으면 이름을 기준으로 오름차순으로 정렬한다.
		List<Member> resultList_04 = query_04.getResultList();
		for (Member member : resultList_04) {
			System.out.println("Member: " + member);
		}
	}

	@Test
	@Order(7)
	@Transactional
	public void JPQLJoin() {
		System.out.println("=== 6. JPQL 조인 ===");
		System.out.println("=== (1) 내부 조인 ===");
		List<Member> resultList_01 = em
				.createQuery("SELECT m FROM Member m INNER JOIN m.team t " + "WHERE t.name =:teamName", Member.class) //
				.setParameter("teamName", "팀1") //
				.getResultList();
		// 회원과 팀을 내부 조인해서 '팀1'에 소속된 회원을 모두 조회한다.

		for (Member member : resultList_01) {
			System.out.println("Member: " + member);
		}

		System.out.println("=== (2) 외부 조인 ===");
		TypedQuery<Member> query_02 = em.createQuery("SELECT m FROM Member m LEFT OUTER JOIN m.team t", Member.class);
		List<Member> resultList_02 = query_02.getResultList();

		for (Member member : resultList_02) {
			System.out.println("Member: " + member);
		}

		System.out.println("=== (3) 컬렉션 조인 ===");
		// 일대다 관계나 다대다 관계처럼 컬렉션을 사용하는 곳에 사용한다.
		Query query_03 = em.createQuery("SELECT t, m FROM Team t LEFT JOIN t.members m");
		List resultList_03 = query_03.getResultList();

		for (Object o : resultList_03) {
			Object[] result_03 = (Object[]) o;
			System.out.println("team: " + result_03[0] + " / " + "member: " + result_03[1]);
		}

		System.out.println("=== (4) 세타 조인 ===");
		Query query_04 = em.createQuery("SELECT COUNT(m) FROM Member m, Team t WHERE m.username = t.name");
		int result_04 = Integer.parseInt(String.valueOf(query_04.getSingleResult()));
		// 단순히 타입 캐스팅을 진행하면 Map에 담긴 데이터 타입이기 때문에 오류가 발생한다.
		System.out.println("count: " + result_04);

	}

	@Test
	@Order(8)
	@Transactional
	public void fetchJoin() {
		System.out.println("=== 7. 페치 조인 ===");
		// 성능을 위해서 있는 기능이다.
		System.out.println("=== (1) 엔티티 페치 조인 ===");
		TypedQuery<Member> query_01 = em.createQuery("SELECT m FROM Member m JOIN FETCH m.team", Member.class);
		List<Member> resultList_01 = query_01.getResultList();

		for (Member member : resultList_01) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (2) 컬렉션 페치 조인 ===");
		TypedQuery<Team> query_02 = em.createQuery("SELECT t FROM Team t JOIN FETCH t.members WHERE t.name = '팀1'",
				Team.class);
		List<Team> resultList_02 = query_02.getResultList();

		for (Team team : resultList_02) {
			System.out.println("team: " + team);
		}

	}

	@Test
	@Order(9)
	@Transactional
	public void pathExpression() {
		System.out.println("=== 8. 경로 표현식 ===");
		// 점을 통해서 객체로 탐색을 하는 방식이다. 단일 값 연관 경로만 이어서 탐색할 수 있다. 묵시적 조인이 일어난다.
	}

	@Test
	@Order(10)
	@Transactional
	public void subquery() {
		System.out.println("=== 9. 서브 쿼리 ===");
		System.out.println("=== (1) EXIST ===");
		// 서브쿼리에 결과가 존재하면 참이다.
		TypedQuery<Member> query_01 = em.createQuery(
				"SELECT m FROM Member m WHERE EXISTS (SELECT t from m.team t WHERE t.name = '팀1')", Member.class);
		// 팀 A에 소속된 회원을 조회한다.
		List<Member> resultList_01 = query_01.getResultList();

		for (Member member : resultList_01) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (2) ALL | ANY | SOME ===");
		// ALL: 모든 조건을 만족하면 참이다.
		// ANY / SOME: 조건을 하나라도 만족하면 참이다.
		TypedQuery<Orders> query_02 = em.createQuery(
				"SELECT o FROM Orders o WHERE o.orderAmount = ANY (SELECT p.StockAmount from Product p)", Orders.class);
		// 전체 상품 각각의 재고보다 주문량이 같은 주문들
		List<Orders> resultList_02 = query_02.getResultList();

		for (Orders order : resultList_02) {
			System.out.println("order: " + order);
		}

		System.out.println("=== (3) IN ===");
		// 서브쿼리의 결과 중 하나라도 같은 것이 있으면 참이다.
		TypedQuery<Team> query_03 = em.createQuery(
				"SELECT t FROM Team t WHERE t IN (SELECT t2 FROM Team t2 JOIN t2.members m2 where m2.age >= 5)",
				Team.class);
		// 5세 이상을 보유한 팀
		List<Team> resultList_03 = query_03.getResultList();

		for (Team team : resultList_03) {
			System.out.println("team: " + team);
		}

	}

	@Test
	@Order(11)
	@Transactional
	public void condition() {
		System.out.println("=== 10. 조건식 ===");
		System.out.println("=== (1) BETWEEN ===");
		TypedQuery<Member> query_01 = em.createQuery("SELECT m from Member m WHERE m.age BETWEEN 6 AND 10",
				Member.class);
		// 나이가 6~10인 회원을 조회한다.
		List<Member> resultList_01 = query_01.getResultList();

		for (Member member : resultList_01) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (2) IN ===");
		TypedQuery<Member> query_02 = em.createQuery("SELECT m FROM Member m WHERE m.username IN ('회원1', '회원2')",
				Member.class);
		// 이름이 '회원1'이나 '회원2'인 회원을 조회한다.
		List<Member> resultList_02 = query_02.getResultList();

		for (Member member : resultList_02) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (3) LIKE ===");
		TypedQuery<Member> query_03 = em.createQuery("SELECT m FROM Member m WHERE m.username LIKE '%원%'",
				Member.class);
		// '원'이라는 단어가 들어간 회원을 조회한다.
		List<Member> resultList_03 = query_03.getResultList();

		for (Member member : resultList_03) {
			System.out.println("member: " + member);
		}

		System.out.println("=== (4) NULL ===");
		TypedQuery<Member> query_04 = em.createQuery("SELECT m FROM Member m WHERE m.username IS NOT NULL",
				Member.class);
		// 회원이름이 null이 아는 회원들 조회한다.
		List<Member> resultList_04 = query_04.getResultList();

		for (Member member : resultList_04) {
			System.out.println("member: " + member);
		}

	}

	@Test
	@Order(12)
	@Transactional
	public void namedQuery() {
		System.out.println("=== 11. Named 쿼리(정적 쿼리) ===");
		Member member = em.createNamedQuery("Member.findByUsername", Member.class) //
				.setParameter("username", "회원1") //
				.getSingleResult(); //

		System.out.println("member: " + member);

	}

}
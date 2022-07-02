package com.example.BasicAssociativeRelationMapping_02_BidirectionalAssociation;

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

import com.example.BasicAssociativeRelationMapping_02_BidirectionalAssociation.entities.Member;
import com.example.BasicAssociativeRelationMapping_02_BidirectionalAssociation.entities.Team;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 테스트의 순서를 지정한다.
@Commit
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	// 저장
	@Test
	@Order(1)
	@Transactional
	public void testSave() {

		// 팀1 저장
		Team team1 = new Team();
		team1.setId("team1");
		team1.setName("팀1");
		em.persist(team1);

		// 회원1 저장
		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.setTeam(team1);
		em.persist(member1);

		// 회원2 저장
		Member member2 = new Member();
		member2.setId("member2");
		member2.setUsername("회원2");
		member2.setTeam(team1);
		em.persist(member2);
	}

	// 조회
	@Test
	@Order(2)
	public void queryLoginJoin() {
		String jpql = "select m from Member m join m.team t where " + "t.name=:teamName";

		List<Member> resultList = em.createQuery(jpql, Member.class).setParameter("teamName", "팀1").getResultList();

		for (Member member : resultList) {
			System.out.println("[query] member.username=" + member.getUsername());
		}
	}

	// 수정
	@Test
	@Order(3)
	@Transactional
	public void updateRelation() {
		Team team2 = new Team();
		team2.setId("team2");
		team2.setName("팀2");
		em.persist(team2);

		Member member = em.find(Member.class, "member1");
		member.setTeam(team2);
		em.persist(member);
	}

	// 연관관계 제거 및 삭제
	@Test
	@Order(4)
	@Transactional
	public void deleteEntities() {
		Member member1 = em.find(Member.class, "member1");
		Member member2 = em.find(Member.class, "member2");
		em.remove(member1);
		em.remove(member2);

		Team team1 = em.find(Team.class, "team1");
		Team team2 = em.find(Team.class, "team2");
		em.remove(team1);
		em.remove(team2);

	}

}
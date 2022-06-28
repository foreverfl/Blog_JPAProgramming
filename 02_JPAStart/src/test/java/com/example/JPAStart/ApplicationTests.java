package com.example.JPAStart;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.JPAStart.entities.Member;

@SpringBootTest
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void logic() {
		Member member = new Member();
		String id = "id1";

		member.setId(id);
		member.setUsername("지한");
		member.setAge(2);

		// 등록
		em.persist(member);

		// 수정
		member.setAge(20);

		// 한 건 조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

		// 목록 조회
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList(); // JPQL
		System.out.println("members.size=" + members.size());

		// 삭제
		em.remove(member);
	}

}

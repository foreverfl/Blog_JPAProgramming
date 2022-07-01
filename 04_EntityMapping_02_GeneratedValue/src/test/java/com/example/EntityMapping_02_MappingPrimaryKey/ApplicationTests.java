package com.example.EntityMapping_02_MappingPrimaryKey;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.EntityMapping_02_MappingPrimaryKey.entities.Member;
import com.example.EntityMapping_02_MappingPrimaryKey.entities.RoleType;

@SpringBootTest
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void test() {
		Member member = new Member();
		Date now;

		// id는 자동 생성되므로 임의 생성하지 않는다.
		// 항상 처음으로 값을 저장하므로 id에 1이 저장된다.
		member.setUsername("타로");
		member.setAge(20);
		member.setDescription("예시를 위한 설명란입니다.");
		member.setRoleType(RoleType.ADMIN);

		now = new Date();
		member.setCreatedDate(now);

		// 등록
		em.persist(member);

		// 수정
		now = new Date();
		member.setLastModifiedDate(now);
		member.setAge(30);

		// 한 건 조회
		Member findMember = em.find(Member.class, 1);
		System.out.println(findMember);

		// 목록 조회
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList(); // JPQL
		System.out.println("members.size=" + members.size());

		// 삭제
		em.remove(member);
	}

}

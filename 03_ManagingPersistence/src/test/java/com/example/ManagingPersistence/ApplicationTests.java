package com.example.ManagingPersistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ManagingPersistence.entities.Member;

@SpringBootTest
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void test() {
		// 비영속: 객체를 생성한 상태
		Member member = new Member();
		member.setId("id1");
		member.setUsername("타로");
		member.setAge(2);

		// 영속: 객체를 저장한 상태
		em.persist(member);

		// 데이터의 조회는 '1차 캐시 -> 데이터베이스' 순으로 이루어진다.
		Member member_01 = em.find(Member.class, "id1");
		Member member_02 = em.find(Member.class, "id1");

		// 1차 캐시에 저장된 두 객체는 동일성이 보장된다.
		System.out.println("member_01과 member_02는 동일한가요? " + (member_01 == member_02));

		// 준영속: 회원 엔티티를 영속성 컨텍스트에서 분리
		em.detach(member);

		// 병합: merge는 준영속 상태의 엔티티를 다시 영속 상태를 사용한다.
		Member mergedMember = em.merge(member);
		System.out.println("em2는 member를 포함하나요? " + (em.contains(member)));
		System.out.println("em2는 mergedMember를 포함하나요? " + (em.contains(mergedMember)));

		// 삭제: 객체를 삭제한 상태
		em.remove(member);
	}

}

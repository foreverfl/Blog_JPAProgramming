package com.example.ManaingProxyAndAssociativeRelation_02_EagerLoadingAndLazyLoading;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ManaingProxyAndAssociativeRelation_02_EagerLoadingAndLazyLoading.entities.Member;
import com.example.ManaingProxyAndAssociativeRelation_02_EagerLoadingAndLazyLoading.entities.Team;

@SpringBootTest
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void test() {
		Member member = em.getReference(Member.class, "id1");

		boolean isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);

		System.out.println("isLoad= " + isLoad);
	}

	@Transactional
	public void printUserAndTeam(String memberId) {
		Member member = em.find(Member.class, memberId);
		Team team = member.getTeam();
		System.out.println("회원 이름: " + member.getUsername());
		System.out.println("소속팀: " + team.getName());
	}

	@Transactional
	public void printUser(String memberId) {
		Member member = em.find(Member.class, memberId);
		System.out.println("회원 이름: " + member.getUsername());
	}

}

package com.example.ManaingProxyAndAssociativeRelation_01_Proxy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ManaingProxyAndAssociativeRelation_01_Proxy.entities.Member;
import com.example.ManaingProxyAndAssociativeRelation_01_Proxy.entities.MemberProxy;
import com.example.ManaingProxyAndAssociativeRelation_01_Proxy.entities.Team;

@SpringBootTest
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void test() {
		boolean isLoad;

		Member member_01 = em.getReference(Member.class, "id1");
		isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member_01);
		System.out.println("isLoad= " + isLoad);

		MemberProxy member_02 = em.getReference(MemberProxy.class, "id1");
		isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member_02);
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

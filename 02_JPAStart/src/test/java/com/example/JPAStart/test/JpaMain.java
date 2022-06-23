package com.example.JPAStart.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.example.JPAStart.entities.Member;

public class JpaMain {

	public static void main(String[] args) {

  
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {


            tx.begin();
            logic(em);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

	public static void logic(EntityManager em) {

		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("taro");
		member.setAge(3);

		// assignment
		em.persist(member);

		// update
		member.setAge(30);

		// selecting one
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

		// selecting a list
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size=" + members.size());

		// delete
		em.remove(member);

	}
}

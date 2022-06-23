package com.example.entityMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		// 엔티티 팩토리를 생성한다.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("entityMapping");
		
		// 엔티티 매니져를 생성한다.
		EntityManager em = emf.createEntityManager();
		
		// 트랜잭션을 생성한다.
		EntityTransaction tx = em.getTransaction();

		try {

			// 트랜잭션을 실행하고 커밋한다.
			tx.begin();
			tx.commit();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			// 오류가 생겼을 때 롤백한다.
			tx.rollback();
			
		} finally {
			
			// 엔티티 매니져를 종료한다.
			em.close();
			
		}

		// 앤티티 팩토리를 종료한다.
		emf.close();
	}

}

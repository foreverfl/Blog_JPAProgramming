package com.example.EntityMapping_03_Summary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void test() {
	}

}

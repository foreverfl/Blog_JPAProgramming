package com.example._AdvancedMapping_03_TablePerConcreteClassStrategy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Commit
class ApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Order(1)
	@Transactional
	public void test() {

	}
}
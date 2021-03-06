package com.example.MakingWebApplication.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.MakingWebApplication.domain.item.Item;

@Repository
public class ItemRepository {

	@PersistenceContext
	EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}
}

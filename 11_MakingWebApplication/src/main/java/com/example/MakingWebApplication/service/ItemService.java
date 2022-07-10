package com.example.MakingWebApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MakingWebApplication.domain.item.Item;
import com.example.MakingWebApplication.repository.ItemRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}

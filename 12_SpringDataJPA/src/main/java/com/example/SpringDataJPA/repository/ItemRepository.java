package com.example.SpringDataJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringDataJPA.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}

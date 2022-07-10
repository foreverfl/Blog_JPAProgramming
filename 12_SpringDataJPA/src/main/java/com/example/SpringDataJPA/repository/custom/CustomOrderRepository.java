package com.example.SpringDataJPA.repository.custom;

import java.util.List;

import javax.persistence.criteria.Order;

import com.example.SpringDataJPA.domain.OrderSearch;

public interface CustomOrderRepository {

	public List<Order> search(OrderSearch orderSearch);

}

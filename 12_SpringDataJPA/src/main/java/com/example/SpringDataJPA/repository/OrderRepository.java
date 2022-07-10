package com.example.SpringDataJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.SpringDataJPA.domain.Order;
import com.example.SpringDataJPA.repository.custom.CustomOrderRepository;

public interface OrderRepository
		extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>, CustomOrderRepository {

}

package com.example.SpringDataJPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringDataJPA.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByName(String name);
}

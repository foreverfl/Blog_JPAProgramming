package com.example.ManaingProxyAndAssociativeRelation.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	private String username;
	private Integer age;

	@ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩: 사용하는 순간 즉시 연관된 모든 객체를 로딩한다.
	@JoinColumn(name = "TEAM_ID") // 외래키
	private Team team;

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY) // 지연 로딩: 프록시 객체를 사용하여 로딩을 지연한다.
	private List<Orders> orders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

}

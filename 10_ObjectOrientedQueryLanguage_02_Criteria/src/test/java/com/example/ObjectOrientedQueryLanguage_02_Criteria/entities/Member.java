package com.example.ObjectOrientedQueryLanguage_02_Criteria.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Member.findByUsername", query = "SELECT m FROM Member m WHERE m.username = :username")
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private long id;

	private String username;

	private int age;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TEAM_ID") // 외래키
	Team team;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Orders> orders = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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

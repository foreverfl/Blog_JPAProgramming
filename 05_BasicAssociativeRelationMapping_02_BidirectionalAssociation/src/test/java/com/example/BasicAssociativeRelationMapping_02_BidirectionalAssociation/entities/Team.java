package com.example.BasicAssociativeRelationMapping_02_BidirectionalAssociation.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id
	@Column(name = "TEAM_ID")
	private String id;

	private String name;

	// 단방향 연관관계와는 다른 부분이다.
	@OneToMany(mappedBy = "team") // 외래키의 주인이 아님을 지정한다.
	private List<Member> members = new ArrayList<>();

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

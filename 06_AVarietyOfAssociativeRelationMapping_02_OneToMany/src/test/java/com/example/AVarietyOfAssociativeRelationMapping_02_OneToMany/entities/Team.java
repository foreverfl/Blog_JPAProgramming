package com.example.AVarietyOfAssociativeRelationMapping_02_OneToMany.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id
	@Column(name = "TEAM_ID")
	private String id;

	private String name;

	@OneToMany // 'mappedBy'가 붙지 않더라도 연관관계의 주인이 될 수 없다. 외래키는 항당 다쪽에 존재한다.
	@JoinColumn(name = "TEAM_ID")
	private List<Member> members = new ArrayList<>();

	public void addMember(Member member) {
		this.members.add(member);
		if (member.getTeam() != this)
			member.setTeam(this);
	}

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

package com.example.BasicAssociativeRelationMapping_02_BidirectionalAssociation.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	@Id
	@Column(name = "MEMBER_ID")
	private String id;

	private String username;

	@ManyToOne // 다대일 연관관계 매핑
	@JoinColumn(name = "TEAM_ID") // 외래키 매핑
	private Team team;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Team getTeam() {
		return team;
	}

	// 단방향 연관관계와는 다른 부분이다.
	public void setTeam(Team team) {
		// 기존 팀과의 관계를 제거
		if (this.team != null)
			this.team.getMembers().remove(this);

		this.team = team;
		team.getMembers().add(this); // 주인이 아닌 곳에도 값을 입력해줘야 안전하다.
	}
}

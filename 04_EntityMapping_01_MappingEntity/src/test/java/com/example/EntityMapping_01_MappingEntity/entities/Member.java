package com.example.EntityMapping_01_MappingEntity.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "MEMBER", uniqueConstraints = { // 매핑할 테이블 이름
		@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = { "NAME", "AGE" }) }) // 스키마 자동 생성 기능에 적용된다.
public class Member {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME", nullable = false, length = 10) // nullable과 length는 제약조건이다.
	private String username;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Lob // CLOB, BLOB 타입을 매핑한다.
	private String description;

	@Transient // 임시로 객체를 저장하기 위한 공간이다.
	private String temp;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", age=" + age + ", roleType=" + roleType
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", description="
				+ description + ", temp=" + temp + "]";
	}
}

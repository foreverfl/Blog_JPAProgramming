package com.example._AdvancedMapping_05_IdClass.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parent {

	@Id
	@Column(name = "PARENT_ID")
	private String id; // 복합키기 때문에 @GeneratedValue는 사용될 수 없다.
	private String name;

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

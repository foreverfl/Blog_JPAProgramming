package com.example.AdvancedMapping_07_NonIdentifyingRelationship.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parent {

	@Id
	@GeneratedValue
	@Column(name = "PARENT_ID")
	private String id; 
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

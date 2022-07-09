package com.example.AdvancedMapping_07_NonIdentifyingRelationship.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GrandChild {

	@Id
	@GeneratedValue
	@Column(name = "GRANDCHILD_ID")
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "CHILD_ID")
	private Child child;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

}

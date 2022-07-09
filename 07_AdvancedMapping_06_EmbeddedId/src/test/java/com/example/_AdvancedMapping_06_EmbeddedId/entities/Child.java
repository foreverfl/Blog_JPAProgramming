package com.example._AdvancedMapping_06_EmbeddedId.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Child {

	@EmbeddedId
	private ChildId childId;

	@MapsId("parentId") // ChildId.parentId 매핑
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public Parent parent;

	private String name;

	public Parent getParent() {
		return parent;
	}

	public ChildId getChildId() {
		return childId;
	}

	public void setChildId(ChildId childId) {
		this.childId = childId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

}

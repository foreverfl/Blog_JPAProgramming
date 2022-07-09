package com.example._AdvancedMapping_06_EmbeddedId.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class GrandChild {

	@EmbeddedId
	private GrandChildId id;

	@MapsId("childId") // GrandChildId.childId 매핑
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PARENT_ID"), @JoinColumn(name = "CHILD_ID") })
	public Child child;

	private String name;

	public GrandChildId getId() {
		return id;
	}

	public void setId(GrandChildId id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package com.example.ManaingProxyAndAssociativeRelation_01_Proxy.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

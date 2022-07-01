package com.example.ManaingProxyAndAssociativeRelation_02_EagerLoadingAndLazyLoading.entities;

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

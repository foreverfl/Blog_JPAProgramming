package com.example.ManaingProxyAndAssociativeRelation_02_EagerLoadingAndLazyLoading.entities;

public class MemberProxy extends Member {

	Member target = null;

	public String getName() {
		if (target == null) {
			this.target = null;
		}

		return target.getUsername();
	}

}

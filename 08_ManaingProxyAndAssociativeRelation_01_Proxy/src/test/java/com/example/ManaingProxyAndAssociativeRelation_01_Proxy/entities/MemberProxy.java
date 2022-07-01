package com.example.ManaingProxyAndAssociativeRelation_01_Proxy.entities;

public class MemberProxy extends Member {

	Member target = null;

	public String getName() {
		if (target == null) {
			this.target = target;
		}

		return target.getUsername();
	}

}

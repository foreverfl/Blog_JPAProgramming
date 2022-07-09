package com.example._AdvancedMapping_05_IdClass.entities;

import java.io.Serializable;
import java.util.Objects;

public class GrandChildId implements Serializable {

	private ChildId child; // GrandChilkd.child 매핑
	private String id; // GrandChild.id 매핑

	public ChildId getChild() {
		return child;
	}

	public void setChild(ChildId child) {
		this.child = child;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(child, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrandChildId other = (GrandChildId) obj;
		return Objects.equals(child, other.child) && Objects.equals(id, other.id);
	}

}

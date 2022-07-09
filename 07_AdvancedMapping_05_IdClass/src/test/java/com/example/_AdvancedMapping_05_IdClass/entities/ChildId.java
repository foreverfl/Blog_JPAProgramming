package com.example._AdvancedMapping_05_IdClass.entities;

import java.io.Serializable;
import java.util.Objects;

public class ChildId implements Serializable {

	private String parent; // Child.parent 매핑
	private String childId; // Child.childId 매핑

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(childId, parent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChildId other = (ChildId) obj;
		return Objects.equals(childId, other.childId) && Objects.equals(parent, other.parent);
	}

}

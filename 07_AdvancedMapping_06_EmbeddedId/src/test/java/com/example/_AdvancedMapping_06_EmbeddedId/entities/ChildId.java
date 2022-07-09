package com.example._AdvancedMapping_06_EmbeddedId.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChildId implements Serializable {

	private String parentId; // @MapsId("parentId")로 매핑

	@Column(name = "CHILD_ID")
	private String id;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, parentId);
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
		return Objects.equals(id, other.id) && Objects.equals(parentId, other.parentId);
	}

}

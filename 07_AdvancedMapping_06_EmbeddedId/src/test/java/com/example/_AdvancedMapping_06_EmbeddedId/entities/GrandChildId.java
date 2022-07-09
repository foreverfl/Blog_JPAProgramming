package com.example._AdvancedMapping_06_EmbeddedId.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GrandChildId implements Serializable {

	private ChildId childId; // @MapsId("childId")로 매핑

	@Column(name = "GRANDCHILD_ID")
	private String id;

	public ChildId getChildId() {
		return childId;
	}

	public void setChildId(ChildId childId) {
		this.childId = childId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(childId, id);
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
		return Objects.equals(childId, other.childId) && Objects.equals(id, other.id);
	}

}

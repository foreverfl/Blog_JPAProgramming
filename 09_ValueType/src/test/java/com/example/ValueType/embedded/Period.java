package com.example.ValueType.embedded;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Period {
	@Temporal(TemporalType.DATE)
	java.util.Date startDate;

	@Temporal(TemporalType.DATE)
	java.util.Date endDate;

	public boolean isWork(Date date) { // 값 타입을 위한 메소드를 정의할 수 있다.
		return false;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

}

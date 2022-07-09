package com.example.ValueType.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String city;

	private String street;

	private String zipcode;

	// 수정자(Setter)를 설정하지 않음으로써 불변객체로 만들었다.
	
	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getZipcode() {
		return zipcode;
	}
	
}

package com.example.ValueType.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.example.ValueType.embedded.Address;
import com.example.ValueType.embedded.Period;

@Entity
public class Member {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Embedded
	Period workPeriod;

	@Embedded
	Address homeAddress;

	// Address 객체를 재사용하기 위해서 재정의했다.
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
			@AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
			@AttributeOverride(name = "zipcode", column = @Column(name = "COMPANY_ZIPCODE")) })
	Address companyAddress;

	// 컬렉션으로 테이블을 만들 수 있다.
	@ElementCollection
	@CollectionTable(name = "FAVORITE_FOODS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
	@Column(name = "FOOD_NAME")
	private Set<String> favoriteFoods = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
	private List<Address> addressHistory = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Period getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(Address companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Set<String> getFavoriteFoods() {
		return favoriteFoods;
	}

	public void setFavoriteFoods(Set<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
	}

	public List<Address> getAddressHistory() {
		return addressHistory;
	}

	public void setAddressHistory(List<Address> addressHistory) {
		this.addressHistory = addressHistory;
	}

}

package com.example.SpringBootJPA.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="COUNTRIES")
public class CountriesEntity {	
	
	@Id @Column(name="COUNTRY_ID")
	private Long countryId;
	
	@Column(name="ISO")
	private String iso;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ISO3")
	private String iso3;
	
	@Column(name="NICE_NAME")
	private String niceName;
	
	@Column(name="NUM_CODE")
	private Long numCode;
	
	@Column(name="PHONE_CODE")
	private Long phoneCode;

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getNiceName() {
		return niceName;
	}

	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}

	public Long getNumCode() {
		return numCode;
	}

	public void setNumCode(Long numCode) {
		this.numCode = numCode;
	}

	public Long getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(Long phoneCode) {
		this.phoneCode = phoneCode;
	}
}

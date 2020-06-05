package com.example.SpringBootJPA.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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

}

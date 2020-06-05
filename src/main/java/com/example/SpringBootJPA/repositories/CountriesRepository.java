package com.example.SpringBootJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootJPA.entities.CountriesEntity;

public interface CountriesRepository extends JpaRepository<CountriesEntity, Long> {

}

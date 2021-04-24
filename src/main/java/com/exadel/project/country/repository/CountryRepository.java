package com.exadel.project.country.repository;

import com.exadel.project.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepository extends
        JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
    Country findCountryByName(String name);
}

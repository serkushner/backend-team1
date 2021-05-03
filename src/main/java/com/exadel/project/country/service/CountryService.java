package com.exadel.project.country.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.country.entity.Country;
import com.exadel.project.country.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService extends BaseService<Country, CountryRepository> {

    private final CountryRepository countryRepository;
    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public Country getByName(String name){
        return countryRepository.findCountryByName(name);
    }

    public List<String> getCountriesNames() {
        return findBySpecifications(null, getSort("name")).stream().map(Country::getName).collect(Collectors.toList());
    }
}

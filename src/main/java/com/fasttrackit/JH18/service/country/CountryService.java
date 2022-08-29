package com.fasttrackit.JH18.service.country;

import com.fasttrackit.JH18.exceptions.ResourceNotFoundException;
import com.fasttrackit.JH18.model.country.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final List<Country> countryList;

    @Autowired
    public CountryService(CountryProvider countryProvider) {
        this.countryList = countryProvider.getCountries();
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public List<String> getCountryNames() {
        return getCountryList().stream()
                .map(Country::getName)
                .toList();
    }

    public String getCapitalFromId(int id) {
        return getCountryList().stream()
                .filter(country -> country.getId() == id)
                .map(Country::getCapital)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Country not found!"));
    }

    public long getPopulationFromId(int id) {
        return getCountryList().stream()
                .filter(country -> country.getId() == id)
                .map(Country::getPopulation)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Country not found!"));
    }

    public List<Country> getCountriesInAContinent(String continent) {
        return getCountryList().stream()
                .filter(country -> country.getContinent().equals(continent))
                .toList();
    }

    public List<String> getCountryNeighbours(int id) {
        return getCountryList().stream()
                .filter(country -> country.getId() == id)
                .map(Country::getNeighbours)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Country not found!"));
    }

    public List<Country> getCountriesInContinentWithPopulationLargerThanParameter(String continent, Long population) {
        return getCountryList().stream()
                .filter(country -> country.getContinent().equals(continent))
                .filter(country -> country.getPopulation() > population)
                .toList();
    }

    public List<Country> getCountriesIncludeNeighbourX(String includeNeighbour) {
        return getCountryList().stream()
                .filter(country -> country.getNeighbours().contains(includeNeighbour))
                .toList();
    }

    public List<Country> getCountriesExcludeNeighbourY(String excludeNeighbour) {
        return getCountryList().stream()
                .filter(country -> !country.getNeighbours().contains(excludeNeighbour))
                .toList();
    }

    public List<Country> getCountriesWithNeighbourXButNotY(String includeNeighbour, String excludeNeighbour) {
        return getCountryList().stream()
                .filter(country -> country.getNeighbours().contains(includeNeighbour))
                .filter(country -> !country.getNeighbours().contains(excludeNeighbour))
                .toList();
    }
}

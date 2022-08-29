package com.fasttrackit.JH18.controller.country;

import com.fasttrackit.JH18.model.country.Country;
import com.fasttrackit.JH18.service.country.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("countries")
    public List<Country> getCountryList(
            @RequestParam(required = false) String includeNeighbour,
            @RequestParam(required = false) String excludeNeighbour) {
        if (includeNeighbour != null && excludeNeighbour != null) {
            return countryService.getCountriesWithNeighbourXButNotY(includeNeighbour, excludeNeighbour);
        } else if (includeNeighbour != null) {
            return countryService.getCountriesIncludeNeighbourX(includeNeighbour);
        } else if (excludeNeighbour != null) {
            return countryService.getCountriesExcludeNeighbourY(excludeNeighbour);
        }
        return countryService.getCountryList();
    }

    @GetMapping("countries/names")
    public List<String> getCountryNames() {
        return countryService.getCountryNames();
    }

    @GetMapping("countries/{id}/capital")
    public String getCapitalFromId(@PathVariable int id) {
        return countryService.getCapitalFromId(id);
    }

    @GetMapping("countries/{id}/population")
    public long getPopulationFromId(@PathVariable int id) {
        return countryService.getPopulationFromId(id);
    }

    @GetMapping("continents/{continent}/countries")
    public List<Country> getCountriesInAContinent(@PathVariable String continent, @RequestParam(required = false) Long minPopulation) {
        if (minPopulation != null) {
            return countryService.getCountriesInContinentWithPopulationLargerThanParameter(continent, minPopulation);
        }
        return countryService.getCountriesInAContinent(continent);
    }

    @GetMapping("countries/{id}/neighbours")
    public List<String> getCountryNeighbours(@PathVariable int id) {
        return countryService.getCountryNeighbours(id);
    }
}

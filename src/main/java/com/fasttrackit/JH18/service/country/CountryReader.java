package com.fasttrackit.JH18.service.country;

import com.fasttrackit.JH18.model.country.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryReader implements CountryProvider {

    @Value("${file.countries}")
    private String fileName;

    @Override
    public List<Country> getCountries() {
        List<Country> countryList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            int id = 1;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> tokens = List.of(line.split("\\|"));
                List<String> neighboursList = new ArrayList<>();
                try {
                    neighboursList = List.of(tokens.get(5).split("~"));
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }
                countryList.add(new Country(
                        tokens.get(0),
                        tokens.get(1),
                        Long.parseLong(tokens.get(2)),
                        Double.parseDouble(tokens.get(3)),
                        tokens.get(4),
                        neighboursList,
                        id++
                ));
            }
            return countryList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

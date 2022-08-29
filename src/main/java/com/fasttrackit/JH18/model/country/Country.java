package com.fasttrackit.JH18.model.country;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Country {
    private String name;
    private String capital;
    private long population;
    private double area;
    private String continent;
    private List<String> neighbours;
    private int id;
}

package com.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Director extends Movie{
    private String name;
    private int numberOfMovies;
    private double imdbRatting;
}

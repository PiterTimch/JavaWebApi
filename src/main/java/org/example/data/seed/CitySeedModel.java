package org.example.data.seed;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CitySeedModel {
    private String name;
    private Long countryId;
    private Integer population;
    private String timezone;
    private String mainAirportCode;
    private Double avgMealPrice;
    private Double avgHotelPrice;
    private Boolean hasRecreationalWater;
    private String imageUrl;
}
package org.example.data.dto.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityItemDTO {

    private Long id;

    private String name;

    private String slug;

    private String image;

    private Long countryId;

    private String countryName;

    private Integer population;

    private String timezone;

    private String mainAirportCode;

    private Double avgMealPrice;

    private Double avgHotelPrice;

    private Boolean hasRecreationalWater;

    private String dateCreated;
}

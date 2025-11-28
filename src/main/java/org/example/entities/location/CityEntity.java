package org.example.entities.location;

import jakarta.persistence.*;
import lombok.Data;
import org.example.entities.common.BaseEntity;

@Entity
@Data
@Table(name = "cities")
public class CityEntity extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @Column
    private int population;

    @Column
    private String description;

    @Column
    private String timezone;

    @Column
    private String mainAirportCode;

    @Column
    private double avgMealPrice;

    @Column
    private double avgHotelPrice;

    @Column
    private Boolean hasRecreationalWater;
}

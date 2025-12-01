package org.example.data.mappers;

import org.example.data.dto.location.CityCreateDTO;
import org.example.data.dto.location.CityItemDTO;
import org.example.data.dto.location.CountryCreateDTO;
import org.example.data.dto.location.CountryItemDTO;
import org.example.entities.location.CityEntity;
import org.example.entities.location.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.name", target = "countryName")
    @Mapping(source = "createdAt", target = "dateCreated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CityItemDTO toDto(CityEntity entity);
    @Mapping(target = "image", ignore = true)
    CityEntity fromCreateDTO(CityCreateDTO dto);
}

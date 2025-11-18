package org.example.data.mappers;

import org.example.data.dto.location.CountryCreateDTO;
import org.example.data.dto.location.CountryItemDTO;
import org.example.entities.location.CountryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryItemDTO toDto(CountryEntity category);

    CountryEntity fromCreateDTO(CountryCreateDTO dto);
}

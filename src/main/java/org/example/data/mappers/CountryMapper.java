package org.example.data.mappers;

import org.example.data.dto.location.CountryCreateDTO;
import org.example.data.dto.location.CountryItemDTO;
import org.example.entities.location.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(source = "createdAt", target = "dateCreated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CountryItemDTO toDto(CountryEntity category);
    @Mapping(target = "image", ignore = true)
    CountryEntity fromCreateDTO(CountryCreateDTO dto);
}

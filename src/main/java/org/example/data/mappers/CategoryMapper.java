package org.example.data.mappers;

import org.example.data.dto.product.CategoryCreateDTO;
import org.example.data.dto.product.CategoryItemDTO;
import org.example.entities.product.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryItemDTO toDto(CategoryEntity category);

    CategoryEntity fromCreateDTO(CategoryCreateDTO dto);
}

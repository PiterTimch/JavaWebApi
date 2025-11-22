package org.example.data.mappers;

import org.example.data.dto.account.UserItemDTO;
import org.example.entities.account.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserItemDTO toDto(UserEntity category);
}

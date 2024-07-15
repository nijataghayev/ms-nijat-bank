package com.example.msnijatbank.mapper;

import com.example.msnijatbank.dao.entity.UserEntity;
import com.example.msnijatbank.model.UserDto;
import org.mapstruct.Mapper;

/**
 * @author: nijataghayev
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(UserEntity userEntity);

    UserEntity mapToEntity(UserDto userDto);
}

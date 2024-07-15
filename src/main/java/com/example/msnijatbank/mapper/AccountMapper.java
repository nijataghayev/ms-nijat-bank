package com.example.msnijatbank.mapper;

import com.example.msnijatbank.dao.entity.AccountEntity;
import com.example.msnijatbank.model.AccountDto;
import org.mapstruct.Mapper;

/**
 * @author: nijataghayev
 */

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto mapToDto(AccountEntity accountEntity);

    AccountEntity mapToEntity(AccountDto accountDto);
}

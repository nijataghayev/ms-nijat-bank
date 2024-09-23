package com.example.msnijatbank.mapper;

import com.example.msnijatbank.dao.entity.PaymentEntity;
import com.example.msnijatbank.model.PaymentDto;
import org.mapstruct.Mapper;

/**
 * @author: nijataghayev
 */

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto mapToDto(PaymentEntity paymentEntity);

    PaymentEntity mapToEntity(PaymentDto paymentDto);
}

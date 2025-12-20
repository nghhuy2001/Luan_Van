package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.CustomerAddressDTO;
import com.cantho.luanvan.entity.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerAddressMapper {
    CustomerAddress toEntity(CustomerAddressDTO customerAddressDTO);

    @Mapping(source = "customer.id", target = "customerId")
    CustomerAddressDTO toDTO(CustomerAddress customerAddress);
}

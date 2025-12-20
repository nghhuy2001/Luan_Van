package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.CustomerDTO;
import com.cantho.luanvan.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerDTO customerDTO);

    @Mapping(source = "customer.account.id", target = "accountId")
    CustomerDTO toDTO(Customer customer);
}

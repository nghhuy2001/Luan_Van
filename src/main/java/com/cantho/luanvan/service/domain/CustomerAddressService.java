package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.CustomerAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerAddressService {
    CustomerAddressDTO createCustomerAddressWithIdCustomer(Long idCustomer, CustomerAddressDTO customerAddressDTO);
    CustomerAddressDTO getCustomerAddressById(Long id);
    Page<CustomerAddressDTO> getAllCustomerAddress(Pageable pageable, Long idCustomer);
    void deleteCustomerAddress(Long id);
}

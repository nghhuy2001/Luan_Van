package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    CustomerDTO getCustomerById(Long id);
    Page<CustomerDTO> getAllCustomer(Pageable pageable);
    Page<CustomerDTO> searchCustomer(String name, Pageable pageable);
}

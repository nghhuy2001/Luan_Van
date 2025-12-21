package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.CustomerAddressDTO;
import com.cantho.luanvan.entity.Customer;
import com.cantho.luanvan.entity.CustomerAddress;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.CustomerAddressMapper;
import com.cantho.luanvan.mapper.CustomerMapper;
import com.cantho.luanvan.repository.CustomerAddressRepository;
import com.cantho.luanvan.repository.CustomerRepository;
import com.cantho.luanvan.service.domain.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressServiceIMPL implements CustomerAddressService {
    @Autowired
    private CustomerAddressRepository customerAddressRepo;
    @Autowired
    private CustomerAddressMapper customerAddressMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public CustomerAddressDTO createCustomerAddressWithIdCustomer(Long idCustomer, CustomerAddressDTO customerAddressDTO) {
        Customer customer = customerRepository.getCustomerById(idCustomer).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: "+idCustomer)
        );

        CustomerAddress customerAddress = CustomerAddress.builder()
                .nameAddress(customerAddressDTO.getNameAddress())
                .customer(customer)
                .build();
        return customerAddressMapper.toDTO(customerAddressRepo.save(customerAddress));
    }

    @Override
    public CustomerAddressDTO getCustomerAddressById(Long id) {
        return null;
    }

    @Override
    public Page<CustomerAddressDTO> getAllCustomerAddress(Pageable pageable, Long idCustomer) {
        return customerAddressRepo
                .getCustomerAddressesByCustomerId(idCustomer, pageable)
                .map(customerAddressMapper::toDTO);
    }

    @Override
    public void deleteCustomerAddress(Long idCustomer) {

    }
}

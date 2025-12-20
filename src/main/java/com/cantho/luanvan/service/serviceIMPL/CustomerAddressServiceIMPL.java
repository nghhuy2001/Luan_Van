package com.cantho.luanvan.service.serviceIMPL;

import com.cantho.luanvan.dto.request.CustomerAddressDTO;
import com.cantho.luanvan.dto.request.CustomerDTO;
import com.cantho.luanvan.entity.Customer;
import com.cantho.luanvan.entity.CustomerAddress;
import com.cantho.luanvan.mapper.CustomerAddressMapper;
import com.cantho.luanvan.mapper.CustomerMapper;
import com.cantho.luanvan.repository.CustomerAddressRepository;
import com.cantho.luanvan.service.CustomerAddressService;
import com.cantho.luanvan.service.CustomerService;
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
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public CustomerAddressDTO createCustomerAddressWithIdCustomer(Long idCustomer, CustomerAddressDTO customerAddressDTO) {
        CustomerDTO customerDTO = customerService.getCustomerById(idCustomer);
        Customer customer = customerMapper.toEntity(customerDTO);

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

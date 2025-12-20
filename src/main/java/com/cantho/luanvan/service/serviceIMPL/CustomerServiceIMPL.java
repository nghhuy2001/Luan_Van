package com.cantho.luanvan.service.serviceIMPL;

import com.cantho.luanvan.dto.request.CustomerDTO;
import com.cantho.luanvan.entity.Cart;
import com.cantho.luanvan.entity.Customer;
import com.cantho.luanvan.exception.common.DuplicateResourceException;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.CustomerMapper;
import com.cantho.luanvan.repository.CustomerRepository;
import com.cantho.luanvan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        boolean existingEmail = customerRepository.existsByEmail(customerDTO.getEmail());
        if (existingEmail)
            throw new DuplicateResourceException("Email đã tồn tại !");

        boolean existingPhone = customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber());
        if(existingPhone)
            throw new DuplicateResourceException("Số điện thoại đã tồn tại !");

        if (!customerDTO.getBirthDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày sinh phải trước ngày hôm nay");
        }

        // thieu tim account roi gasn vao nha !!!!

        Customer customer = Customer.builder()
                .birthDate(customerDTO.getBirthDate())
                .email(customerDTO.getEmail())
                .gender(customerDTO.getGender())
                .name(customerDTO.getName())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();

        Cart cart = Cart.builder()
                .totalItems(0)
                .totalPrice(BigDecimal.ZERO)
                .build();
        customer.setCart(cart);

        Customer saved = customerRepository.save(customer);
        return customerMapper.toDTO(saved);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.getCustomerById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: "+ id)
        );
        customer.setName(customerDTO.getName());
        customer.setBirthDate(customerDTO.getBirthDate());
        customer.setGender(customerDTO.getGender());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDTO(saved);
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.getCustomerById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: "+ id)
        );
        return customerMapper.toDTO(customer);
    }

    @Override
    public Page<CustomerDTO> getAllCustomer(Pageable pageable) {
        return customerRepository
                .findAll(pageable)
                .map(customerMapper::toDTO);
    }

    @Override
    public Page<CustomerDTO> searchCustomer(String name, Pageable pageable) {
        return null;
    }
}

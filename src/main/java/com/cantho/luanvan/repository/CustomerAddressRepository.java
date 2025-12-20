package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.CustomerAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    Page<CustomerAddress> getCustomerAddressesByCustomerId(Long id, Pageable pageable);
}

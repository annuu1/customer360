package com.customer360.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer360.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Customer findByMono(String mono);
    Customer findByEmail(String email);
}

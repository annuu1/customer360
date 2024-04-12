package com.customer360.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer360.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    
}

package com.customer360.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer360.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

    
} 

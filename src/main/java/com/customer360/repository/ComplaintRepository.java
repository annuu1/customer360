package com.customer360.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer360.models.Complaint;
import com.customer360.models.Customer;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
List<Complaint> findByStatus(String status);
List<Complaint> findByCustomer(Customer cId);
List<Complaint> findTop5ByOrderByDateDesc();
}

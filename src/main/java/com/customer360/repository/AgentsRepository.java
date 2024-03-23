package com.customer360.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer360.models.Agents;

public interface AgentsRepository extends JpaRepository<Agents, Integer> {
    Agents findByEmail(String email);
}

package com.customer360.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.customer360.models.Users;
import com.customer360.models.Agents;



public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
    Users findByAgent(Agents agent);
}

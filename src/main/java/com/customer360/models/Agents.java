package com.customer360.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Agents", uniqueConstraints = {
    
    @UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "id")

})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agents {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String phone;
    private String role;

    @OneToOne
    private Users user;

}

package com.customer360.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer_name;
    private String pan;
    private String status;
    private String customer_id;
    private String service_id;
    private String order_date;
    private String assessment_year;
    
    private String additional_note;

    public Order(String customer_name, String pan, String status, String customer_id, String service_id,
            String order_date, String assessment_year, String additional_note) {
        this.customer_name = customer_name;
        this.pan = pan;
        this.status = status;
        this.customer_id = customer_id;
        this.service_id = service_id;
        this.order_date = order_date;
        this.assessment_year = assessment_year;
        this.additional_note = additional_note;
    }

    
    
    
}

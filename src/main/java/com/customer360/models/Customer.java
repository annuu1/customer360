package com.customer360.models;

import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames="cid"),
	@UniqueConstraint(columnNames = "mono"),
	@UniqueConstraint(columnNames="email")
})
public class Customer {
	@Id
	private int cid;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Complaint> complaints;

	private String name;
	private String mono;
	private String email;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMono() {
		return mono;
	}
	public void setMono(String mono) {
		this.mono = mono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Customer(String name, String mono, String email) {
		super();
		this.cid = new Random().nextInt(1000);
		this.name = name;
		this.mono = mono;
		this.email = email;
	}
	public Customer() {
		super();

	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", name=" + name + ", mono=" + mono + ", email=" + email + "]";
	}
	
	
	
}

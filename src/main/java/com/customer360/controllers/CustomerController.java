package com.customer360.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer360.models.Complaint;
import com.customer360.models.Customer;
import com.customer360.repository.ComplaintRepository;
import com.customer360.repository.CustomerRepository;

@Controller
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ComplaintRepository complaintRepository;
	
	@RequestMapping("/addCustomer")
	public String addCustomer(@RequestParam String name , @RequestParam String moNo,
		@RequestParam String email, Model m) {
		
		if(customerRepository.findByMono(moNo)!=null&&customerRepository.findByEmail(email)!=null){
			m.addAttribute("error", "The customer is already present");
			return "error";


		}
		else
		{
		Customer customer= new Customer(name, moNo, email);
		customerRepository.save(customer);
		return allCustomers(m);
		}
	}
		@GetMapping("updateCustomer")
		public String updateCustomer(@RequestParam int cid, Model m){
			Customer customer = customerRepository.findById(cid).get();
			m.addAttribute("customer", customer);
			return "updateCustomer";
		}
	
		@RequestMapping("/updateCustomer1")
		public String updateCustomer1(@RequestParam int cid ,@RequestParam String name 
							, @RequestParam String moNo,@RequestParam String email, Model m) {
		
	
		Customer customer= new Customer(name, moNo, email);
		customer.setCid(cid);
		customerRepository.save(customer);
		return allCustomers(m);
	}

	@RequestMapping("/allCustomers")
	public String allCustomers(Model m) {
	    List<Customer> customers = customerRepository.findAll();
	    m.addAttribute("customers", customers);

	    return "allCustomers";
	}
	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam int cid, Model m) {
		Customer customer= customerRepository.findById(cid).orElse(null);
		 if(customer!=null){
			List<Complaint> relatedComplaints=complaintRepository.findByCustomer(customer);

			for(Complaint complaint: relatedComplaints){
				complaint.setCustomer(null);
			}
		 }


		customerRepository.deleteById(cid);
		return allCustomers(m);
	}
	
	@RequestMapping("/getCustomer")
	public String getCustomer(@RequestParam int cid ,Model m) {
		Optional<Customer> cus=customerRepository.findById(cid);
		Customer customer= cus.get();
		m.addAttribute("customer", customer.getName() );
		return "show";
	}
	
	
}

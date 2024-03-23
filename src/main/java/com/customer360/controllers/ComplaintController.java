package com.customer360.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.customer360.models.Complaint;
import com.customer360.models.Customer;
import com.customer360.repository.ComplaintRepository;
import com.customer360.repository.CustomerRepository;

@Controller
public class ComplaintController {
	@Autowired
	private ComplaintRepository repo;
	@Autowired
	private CustomerRepository crepo;

	@RequestMapping("/addComplaint")
	public String addComplaint(@RequestParam String name, @RequestParam String moNo,
			@RequestParam String category, @RequestParam String subject
			,@RequestParam String complaint, @RequestParam int cId, Model m) {

		if(crepo.findById(cId).isPresent()){

		LocalDate raiseDate= LocalDate.now();
		String status="Progress";
		String priority="Medium";
		String assign="Anurag Rajput";
		String resolution=null;
		LocalDate closureDate=null;
		
		Complaint com= new Complaint();
		com.setName(name);
		com.setMoNo(moNo);
		com.setSubject(subject);
		com.setComplaint(complaint);
		com.setDate(raiseDate);
		com.setStatus(status);
		com.setPriority(priority);
		com.setAssign(assign);
		com.setResolution(resolution);
		com.setClosureDate(closureDate);

		Customer cu= new Customer();
		cu.setCid(cId);
		
		com.setCustomer(cu);

		repo.save(com);
		return allComplaints(m);
		}

		else{
			return "redirect:/addCustomerForm";
		}
	}
	@RequestMapping("allComplaints")
	public String allComplaints(Model m) {
		List<Complaint> com=repo.findAll();
		m.addAttribute("complaints",com);
			return "allComplaints";
	}

	@RequestMapping("getStatusComplaints")
	public String getStatusComplaints(@RequestParam String status, Model m) {
		List<Complaint> com=repo.findByStatus(status);
		m.addAttribute("complaints",com);
			return "allComplaints";
	}

	@RequestMapping("deleteComplaint")
	public String deleteComplaint(@RequestParam int cid, Model m) {
		repo.deleteById(cid);
		return allComplaints(m);
	}
	
	@RequestMapping("getComplaintById")
	public String getComplaintById(@RequestParam int cId, Model m) {
		if(cId==0){
			Complaint co= new Complaint();
			co.setCid(0);
			m.addAttribute("complaint", co);
			return "trackComplaint";
		}
		else{
		Optional<Complaint> c= repo.findById(cId);
		
		if(c.isPresent()) {
			Complaint complaint=c.get();
		m.addAttribute("complaint", complaint);
		return "trackComplaint";
			
		}
		else {
			m.addAttribute("error", "The given complaint number is not available <b>"+cId+"</b>");
			return "error";
		}
		}
	}
	
	@RequestMapping("fetchUpdateComplaint")
	public String redirectUpdateComplaint(@RequestParam int cId, Model m) {
		
		Optional<Complaint> c= repo.findById(cId);
		
		if(c.isPresent()) {
			Complaint complaint=c.get();
		m.addAttribute("complaint", complaint);
		return "updateComplaint";
			
		}
		else {
			m.addAttribute("error", "The given complaint number is not available <b>"+cId+"</b>");
			return "error";
		}
	}
	@RequestMapping("updateComplaint")
	public String updateComplaint(@RequestParam int cId , @RequestParam String name, 
			@RequestParam String mobile, @RequestParam String subject,
			@RequestParam String complaint, @RequestParam String status, 
			@RequestParam String priority , @RequestParam String assign,
			@RequestParam String resolution, @RequestParam LocalDate closureDate, Model m){
			

				Optional<Complaint> c=repo.findById(cId);
				Complaint comp= c.get();
				comp.setName(name);
				comp.setMoNo(mobile);
				comp.setSubject(subject);
				comp.setStatus(status);
				comp.setComplaint(complaint);
				comp.setAssign(assign);
				comp.setPriority(priority);
				comp.setResolution(resolution);
				comp.setClosureDate(closureDate);
				
				
				System.out.println("Closure date is-"+closureDate);
				repo.save(comp);
	return allComplaints(m);
}
	
}



		

package com.customer360.controllers;

import java.time.LocalDate;

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
@RequestMapping("public")
public class PublicController {
    @Autowired
	private ComplaintRepository repo;
	@Autowired
	private CustomerRepository crepo;

    @RequestMapping("aboutUs")
    public String aboutUs(){
        return "aboutUs";
    }
    @RequestMapping("home")
    public String home(){
        return "index";
    }
    @RequestMapping("services")
    public String services(){
        return "services";
    }
    @RequestMapping("contact")
    public String contact(){
        return "contact";
    }
    @RequestMapping("raiseComplaint")
    public String raiseComplaint(){
        return "addComplaintPublic";
    }
    
    
	@RequestMapping("/addComplaint")
	public String addComplaint(@RequestParam String name, @RequestParam String moNo,
			@RequestParam String category, @RequestParam String subject
			,@RequestParam String complaint,Model m) {

		// if(crepo.findById(cId).isPresent()){

		Customer cu= new Customer();
		cu.setName(name);
		cu.setMono(moNo);
		crepo.save(cu);


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

		
		com.setCustomer(cu);

		repo.save(com);
	
        return "index";
	}

}
package com.customer360.controllers;

import java.util.List;

import com.customer360.models.Agents;
import com.customer360.models.Complaint;
import com.customer360.models.Order;
import com.customer360.models.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer360.repository.AgentsRepository;
import com.customer360.repository.ComplaintRepository;
import com.customer360.repository.CustomerRepository;
import com.customer360.repository.OrderRepository;
import com.customer360.repository.ServiceRepository;

@Controller
public class DashboardController {
	@Autowired
	private CustomerRepository cuRepo;
	@Autowired
	private ComplaintRepository coRepo;
	@Autowired
	private AgentsRepository agRepo;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ServiceRepository serviceRepository;
	
	@RequestMapping("/dashboard")
	public String dashboard(Model m) {
		Long totalcustomers=cuRepo.count();
		Long totalcomplaints=coRepo.count();
		//Get agents
		List<Agents> agents= agRepo.findAll();
		int totalAgents = agents.size();

		List<Order> orders= orderRepository.findAll();
		int totalOrders = orders.size();
		List<Service> services= serviceRepository.findAll();
		int totalServices = services.size();

		List<Complaint> recentComplaints = coRepo.findTop5ByOrderByDateDesc();
		// int allRecentComplaints = recentComplaints.size();
		
		List<Complaint> progressComplaintList= coRepo.findByStatus("Progress");
		int totalProgressComplaints=progressComplaintList.size();

		List<Complaint> closedComplaintList= coRepo.findByStatus("Closed");
		int totalClosedComplaints=closedComplaintList.size();
		
		List<Complaint> resolvedComplaintList= coRepo.findByStatus("Resolved");
		int totalResolvedComplaints=resolvedComplaintList.size();

		List<Complaint> pendingComplaintList= coRepo.findByStatus("Pending");
		int totalPendingComplaints=pendingComplaintList.size();
		
		m.addAttribute("totalComplaints", totalcomplaints);
		m.addAttribute("totalCustomers", totalcustomers);

		m.addAttribute("allPendingComplaints",totalPendingComplaints);
		m.addAttribute("allProressComplaints",totalProgressComplaints);
		m.addAttribute("allClosedComplaints",totalClosedComplaints);
		m.addAttribute("allResolvedComplaints",totalResolvedComplaints);
		m.addAttribute("recentComplaints",recentComplaints);
		m.addAttribute("totalAgents", totalAgents);

		m.addAttribute("totalOrders", totalOrders);
		m.addAttribute("totalServices", totalServices);
		

		return "dashboard";
	}

		@RequestMapping("/adminDashboard")
		public String adminDashboard(Model m) {
		Long totalcustomers=cuRepo.count();
		Long totalcomplaints=coRepo.count();
		Long totalAgents=agRepo.count();
		m.addAttribute("totalComplaints", totalcomplaints);
		m.addAttribute("totalCustomers", totalcustomers);
		m.addAttribute("totalAgents", totalAgents);
		return "adminDashboard";
	}
}

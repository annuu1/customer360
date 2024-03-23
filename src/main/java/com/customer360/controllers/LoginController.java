package com.customer360.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer360.models.Users;
import com.customer360.repository.AgentsRepository;
import com.customer360.repository.UsersRepository;

import com.customer360.models.Agents;

@Controller
public class LoginController {
    @Autowired
    private AgentsRepository agentRepo;
    @Autowired
    private UsersRepository userRepo;
    @Autowired
    DashboardController dashboardController;
    @Autowired
    private AdminNevigation adminNevigation;

    
    @RequestMapping("/addUser")
    public String addAgent(@RequestParam String name, @RequestParam String email,
    @RequestParam String moNo, @RequestParam String role, Model m){
        String password=moNo;
        Agents agent= new Agents();
            agent.setEmail(email);
            agent.setName(name);
            agent.setPhone(moNo);
            agent.setRole(role.toUpperCase());
        Users user=new Users();
            user.setUsername(email);
            user.setPassword(password);
            user.setRole(role.toUpperCase());
            userRepo.save(user);

            agent.setUser(user);
        agentRepo.save(agent);
        return adminNevigation.components(m);
    }


}


package com.customer360.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer360.models.Agents;
import com.customer360.repository.AgentsRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("admin")
public class AdminNevigation {

    @Autowired
    private AgentsRepository agentsRepository;
     

    @RequestMapping("components")
    public String components(Model m){
        List<Agents> agents = agentsRepository.findAll();
        m.addAttribute("agents", agents);

        return "components";
    }
    @RequestMapping("addUserForm")
    public String addUserForm(){
        return "addUser";
    }
    
}

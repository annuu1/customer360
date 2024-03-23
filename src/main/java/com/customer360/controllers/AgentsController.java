package com.customer360.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer360.repository.AgentsRepository;
import com.customer360.repository.UsersRepository;
import com.customer360.models.Agents;
import com.customer360.models.Users;

@Controller
public class AgentsController {
    @Autowired
    private AgentsRepository repo;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AdminNevigation adminNevigation;

    @RequestMapping("allAgents")
    public String allAgents(Model m){
        List<Agents> agents=repo.findAll();
        m.addAttribute("agents", agents);
        return "admin/allAgents";
    }
        @RequestMapping("viewAgent")
    public String viewAgent(@RequestParam int id, Model m){
        Optional<Agents> ag=repo.findById(id);
        Agents agent=ag.get();
        m.addAttribute("agent", agent);
        return "admin/allAgents";
    }
    @SuppressWarnings("null")
    @RequestMapping("admin/deleteUser")
    public String deleteUser(@RequestParam int id, Model m){
        Agents agent = repo.findById(id).orElse(null);
        usersRepository.deleteById(agent.getUser().getUsername());
        repo.deleteById(id);

        return adminNevigation.components(m);
    }
    @GetMapping("admin/updateUser")
    public String updateUser1(@RequestParam int id, Model m){
        Agents agent = repo.findById(id).orElse(null);
        m.addAttribute("agent", agent);
        m.addAttribute("id", id);

        return "updateUser";
    }
    @RequestMapping("admin/updateUser1")
    public String udpateUser(@RequestParam int id, @RequestParam String name, @RequestParam String email,
    @RequestParam String moNo, @RequestParam String role, Model m){
        String password=moNo;
        Agents agent= new Agents();
            agent.setId(id);
            agent.setEmail(email);
            agent.setName(name);
            agent.setPhone(moNo);
            agent.setRole(role.toUpperCase());
        // String encodedPass=WebSecurityConfig.passwordEncoder().encode(password);
        Users user=new Users();
            user.setUsername(email);
            user.setPassword(password);
            user.setRole(role.toUpperCase());
            usersRepository.save(user);

            agent.setUser(user);
        repo.save(agent);
        return adminNevigation.components(m);
    }

    }

package com.customer360.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer360.models.Service;
import com.customer360.repository.ServiceRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ServiceController {
    @Autowired ServiceRepository service_repo;
    
    @RequestMapping("add_service")
    public String add_service(Model m){
        return "ca/addService";
    }
    @RequestMapping("save_service")
    public String save_service(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam String price,
                                Model m
                                                        ) {

        Service service = new Service(name, description, price);
        service_repo.save(service);
        System.out.println(
            service_repo.findAll()
        );
        return all_services(m);
    }
    @RequestMapping("update_services")
    public String update_services(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam String price,
                                Model m
                                                        ) {

        Service service = new Service(id, name, description, price);
        service_repo.save(service);
        System.out.println(
            service_repo.findAll()
        );
        return all_services(m);
    }
    @RequestMapping("all_services")
    public String all_services(Model model){
        List<Service> all_services = service_repo.findAll();
        model.addAttribute("all_services", all_services);
        return "ca/services";
    }
    @RequestMapping("delete_service")
    public String delete_service(@RequestParam Long id, Model m){
        service_repo.deleteById(id);
        return all_services(m);
    }

    @RequestMapping("update_service")
    public String update_service(Model m)
    {
        return "ca/updateService";
    }
}

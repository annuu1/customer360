package com.customer360.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class NavigationController {

    // front end from here
    @RequestMapping("/workspace")
    public String workspace(){
        return "ca/workSpace";
    }
@RequestMapping("/allOders")
public String allOrdere(){
    return "ca/ordersList";
}
@RequestMapping("/previousYear")
public String previousYear(){
    return "ca/previousYear";
}
@RequestMapping("/team")
public String team(){
    return "ca/team";
}

@RequestMapping("services")
    public String services(){
        return "/ca/services";
    }





//Agents mapping from here
    @RequestMapping("login")
    public String showAgentLogin(){
        return "login";
    }
        @RequestMapping("addAgentsForm")
    public String addAgentsForm(){
        return "/admin/addAgent";
    }




    //Admin navigation form here

    @RequestMapping("showAdminLogin")
    public String showAdminLogin(){
        return "admin/adminLogin";
    }

    @RequestMapping("test")
    public String test(){
        return "addComplaint";
    }
    @RequestMapping("addComplaintForm")
    public String addComplaintForm(){
        return "addComplaint";
    }
    @RequestMapping("addCustomerForm")
    public String addCustomerForm(){
        return "addCustomer";
    }

   @RequestMapping("addUserForm1")
    public String addUserForm(){
        return "addUser";
    }
    

}

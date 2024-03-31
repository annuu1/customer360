package com.customer360.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customer360.models.Order;
import com.customer360.repository.OrderRepository;

@Controller
public class OrdersController {
    @Autowired
    private OrderRepository order_repo;

    @RequestMapping("/saveOrder")
    public String saveOrder(
            @RequestParam String customer_name,
            @RequestParam String pan,
            @RequestParam String status,
            @RequestParam String customer_id,
            @RequestParam String service_id,
            @RequestParam String order_date,
            @RequestParam String assessment_year,
            @RequestParam String additional_note,
            RedirectAttributes redirectAttributes) {
        Order order = new Order(customer_name, pan, status, customer_id
                            , service_id, order_date, assessment_year, additional_note);

        order_repo.save(order);

        redirectAttributes.addFlashAttribute("message", "Order saved successfully!");

        return "redirect:/order_saved";
     }

    @RequestMapping("/allOrders")
    public String allOrders(Model m){
        List<Order> all_Orders = order_repo.findAll();
        m.addAttribute("all_Orders", all_Orders);
        return "ca/all_orders";
    }

    @RequestMapping("/order_saved")
public String orderSaved(Model model) {
    List<Order> all_Orders = order_repo.findAll();
        model.addAttribute("all_Orders", all_Orders);
    // Retrieve the flash attribute (if available)
    String message = (String) model.getAttribute("message");
    if (message != null) {
        // Pass the message to the template
        model.addAttribute("message", message);
    }
    return "ca/all_orders"; 
}
    @RequestMapping("/addOrder")
    public String addOrder(Model model) {
        
        return "ca/addOrder";
}

@RequestMapping("previous_year_orders")
public String previous_year_orders(Model m){
    List<Order> all_orders = order_repo.findAll();
    List<Order> previous_year_orders = new ArrayList<>();
    String get_current_assessment_year = get_current_assessment_year();
    
    for (Order order : all_orders) {
        if (!order.getAssessment_year().equals(get_current_assessment_year)) {
            previous_year_orders.add(order);
        }
    }
    m.addAttribute("all_Orders", previous_year_orders);
    System.out.println(previous_year_orders);
    return "ca/all_orders";
}
// Utility methods are here
private String get_current_assessment_year() {
    int currentYear = LocalDate.now().getYear();
    String assessment_year = String.valueOf(currentYear).substring(2) + "-" + String.valueOf(currentYear + 1).substring(2);;
    System.out.println(assessment_year);
    return assessment_year;
}


}

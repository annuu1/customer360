package com.customer360.controllers;

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
        System.out.println("Entered to the save order");
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
        System.out.println(all_Orders);
        return "ca/all_orders";
    }

    @RequestMapping("/order_saved")
public String orderSaved(Model model) {
    // Retrieve the flash attribute (if available)
    String message = (String) model.getAttribute("message");
    if (message != null) {
        // Pass the message to the template
        model.addAttribute("message", message);
    }
    return "order_saved"; // Return the "order_saved" page
}


}

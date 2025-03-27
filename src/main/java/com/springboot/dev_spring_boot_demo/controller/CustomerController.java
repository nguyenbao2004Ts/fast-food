package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Customer;
import com.springboot.dev_spring_boot_demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list-customer")
    public String list(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "list-customer";
    }

    @GetMapping("/customer-form-insert")
    public String customerFormInsert(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form-insert";
    }

    @PostMapping("/save")
    public String save(@Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "customer-form-insert";
        }
        customerService.save(customer);
        return "redirect:/customer/list-customer";
    }

    @GetMapping("/customer-form-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        customerService.deleteById(id);
        return "redirect:/customer/list-customer";
    }
}
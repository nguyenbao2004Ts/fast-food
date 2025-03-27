package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Student;
import com.springboot.dev_spring_boot_demo.service.FoodService;
import com.springboot.dev_spring_boot_demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private StudentService studentService;
    private FoodService foodService;

    public HomeController(StudentService studentService , FoodService foodService) {
        this.studentService = studentService;
        this.foodService = foodService;
    }



    @GetMapping("/american-food")
    public String home() {
        return "index";  // Trỏ đến templates/index.html
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }

    @GetMapping("/service")
    public String service() {
        return "Service";
    }

    @GetMapping("/pricing")
    public String pricing() {
        return "pricing";
    }

    @GetMapping("/order")
    public String showOrderPage(@RequestParam(value = "plan", required = false) String plan, Model model) {
        model.addAttribute("foods", foodService.findAll());
        if (plan != null) {
            model.addAttribute("selectedPlan", plan);
        }
        return "order";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
    @GetMapping("/blog/post1")
    public String blogPost1() {
        return "blog-post1"; // You'll need to create this template
    }

    @GetMapping("/blog/post2")
    public String blogPost2() {
        return "blog-post2"; // You'll need to create this template
    }

    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact-us";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/detailStudent")
    public String detailStudent(@RequestParam("id") int id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "detailStudent";
    }

    @GetMapping("/shopping cart")
    public String shoppingCart() {
        return "shopping-cart";
    }

}

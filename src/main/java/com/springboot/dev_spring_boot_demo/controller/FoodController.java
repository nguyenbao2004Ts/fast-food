package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Food;
import com.springboot.dev_spring_boot_demo.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/foods")
public class FoodController {
    private final FoodService foodService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/default/images";

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/list-food")
    public String list(Model model) {
        model.addAttribute("foods", foodService.findAll());
        return "list-food";
    }

    @GetMapping("/food-form-insert")
    public String formInsert(Model model) {
        model.addAttribute("food", new Food());
        return "food-form-insert";
    }

    @PostMapping("/save")
    public String save(Food food, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            // Xử lý upload ảnh mới
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String originalFileName = imageFile.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID() + fileExtension;

            Path filePath = Paths.get(UPLOAD_DIRECTORY, newFileName);
            Files.write(filePath, imageFile.getBytes());

            // Xóa ảnh cũ nếu có (khi update)
            if (food.getId() != 0 && food.getImageURL() != null) {
                String oldImage = food.getImageURL().replace("/default/images/", "");
                Path oldPath = Paths.get(UPLOAD_DIRECTORY, oldImage);
                if (Files.exists(oldPath)) Files.delete(oldPath);
            }

            food.setImageURL("/default/images/" + newFileName);
        } else if (food.getId() == 0) {
            // Nếu là thêm mới mà không có ảnh
            throw new IllegalArgumentException("Food image is required");
        }

        foodService.save(food);
        return "redirect:/foods/list-food";
    }

    @GetMapping("/food-form-update")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Food food = foodService.findById(id);
        if (food == null) throw new IllegalArgumentException("Invalid food Id:" + id);
        model.addAttribute("food", food);
        return "food-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) throws IOException {
        Food food = foodService.findById(id);
        if (food.getImageURL() != null) {
            String imageName = food.getImageURL().replace("/default/images/", "");
            Path imagePath = Paths.get(UPLOAD_DIRECTORY, imageName);
            if (Files.exists(imagePath)) Files.delete(imagePath);
        }
        foodService.deleteById(id);
        return "redirect:/foods/list-food";
    }
}
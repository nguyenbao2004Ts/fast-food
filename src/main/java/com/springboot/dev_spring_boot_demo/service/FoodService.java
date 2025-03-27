package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.entity.Food;
import java.util.List;

public interface FoodService {
    List<Food> findAll();
    Food findById(int id);
    Food save(Food food);
    void deleteById(int id);
}
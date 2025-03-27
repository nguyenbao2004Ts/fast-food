package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.dao.FoodDAO;
import com.springboot.dev_spring_boot_demo.entity.Food;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImp implements FoodService {
    private FoodDAO foodDAO;

    @Autowired
    public FoodServiceImp(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    @Override
    public List<Food> findAll() {
        return foodDAO.findAll();
    }

    @Override
    public Food findById(int id) {
        return foodDAO.findById(id);
    }

    @Override
    public Food save(Food food) {
        return foodDAO.save(food);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        foodDAO.deleteById(id);
    }
}
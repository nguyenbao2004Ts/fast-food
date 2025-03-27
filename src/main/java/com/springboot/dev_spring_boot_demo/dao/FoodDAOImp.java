package com.springboot.dev_spring_boot_demo.dao;

import com.springboot.dev_spring_boot_demo.entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodDAOImp implements FoodDAO {
    private EntityManager em;

    @Autowired
    public FoodDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Food> findAll() {
        TypedQuery<Food> query = em.createQuery("from Food", Food.class);
        return query.getResultList();
    }

    @Override
    public Food findById(int id) {
        return em.find(Food.class, id);
    }

    @Override
    @Transactional
    public Food save(Food food) {
        Food saved = em.merge(food);
        return saved;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Food food = em.find(Food.class, id);
        em.remove(food);
    }
}
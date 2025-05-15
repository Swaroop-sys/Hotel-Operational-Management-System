package com.example.demo.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Task.Entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{

}

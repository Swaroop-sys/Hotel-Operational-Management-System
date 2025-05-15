package com.example.demo.Task.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Task.Entity.Food;
import com.example.demo.Task.Repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	public Food saveFood(Food food) {
		String randomId = UUID.randomUUID().toString();
		food.setFoodId(randomId);
		return foodRepository.save(food);
	}
	public List<Food> getAllFood(){
		return foodRepository.findAll();
	}
}

package com.example.demo.Task.Controller;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Task.Entity.Food;
import com.example.demo.Task.Services.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@PostMapping("/save")
	 public ResponseEntity<Food> saveFood(
//	            @RequestParam("foodId") String foodId,
	            @RequestParam("productName") String productName,
	            @RequestParam("productPrice") int productPrice,
	            @RequestParam("description") String description,
	            @RequestParam("image") MultipartFile image) {
	        try {
	            // Convert MultipartFile to byte[]
	            byte[] imageBytes = image.getBytes();

	        	String randomId = UUID.randomUUID().toString();
	
	            // Create Food object
	            Food food = new Food(randomId, imageBytes, productName, productPrice, description);

	            // Save the food object
	            Food savedFood = foodService.saveFood(food);

	            return ResponseEntity.status(HttpStatus.CREATED).body(savedFood);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	@GetMapping("/get")
	ResponseEntity <List<Food>> getAllFood(){
		
		 List<Food> foodList = foodService.getAllFood();

		    // Convert food images to Base64
		 List<Food> foodDTOList = foodList.stream()
			        .map(food -> new Food(
			                food.getFoodId(),
			                Base64.getDecoder().decode(Base64.getEncoder().encodeToString(food.getImage())), // decode back to byte[]
			                food.getProductName(),
			                food.getProductPrice(),
			                food.getDescription()
			        ))
			        .collect(Collectors.toList());


		    return ResponseEntity.ok(foodDTOList);
	}
	@GetMapping("/userget")
	ResponseEntity <List<Food>> getAllUserFood(){
		
		 List<Food> foodList = foodService.getAllFood();

		    // Convert food images to Base64
		 List<Food> foodDTOList = foodList.stream()
			        .map(food -> new Food(
			                food.getFoodId(),
			                Base64.getDecoder().decode(Base64.getEncoder().encodeToString(food.getImage())), // decode back to byte[]
			                food.getProductName(),
			                food.getProductPrice(),
			                food.getDescription()
			        ))
			        .collect(Collectors.toList());


		    return ResponseEntity.ok(foodDTOList);
	}
}

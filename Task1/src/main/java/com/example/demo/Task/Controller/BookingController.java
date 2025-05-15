package com.example.demo.Task.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Task.Entity.Booking;
import com.example.demo.Task.Services.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/save")
	public Booking saveBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	@GetMapping("/get")
	public ResponseEntity<List<Booking>> getAllBooking(){
		return ResponseEntity.ok(bookingService.getAllBooking());
	}
}

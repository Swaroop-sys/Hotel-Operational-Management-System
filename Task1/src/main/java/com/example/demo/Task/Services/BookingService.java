package com.example.demo.Task.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Task.Entity.Booking;
import com.example.demo.Task.Repository.BookingRepository;

@Service
public class BookingService {
@Autowired
private BookingRepository bookingRepository;

public Booking saveBooking(Booking booking) {

	return bookingRepository.save(booking);
}
public List<Booking> getAllBooking(){
	return bookingRepository.findAll();
}
}

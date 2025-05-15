package com.example.demo.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Task.Entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

}

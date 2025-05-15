package com.example.demo.Task.Entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String email;

	    @JsonFormat(pattern = "dd/MM/yyyy")  // Format for the date in the JSON payload
	    private LocalDate dateField;

	    @JsonFormat(pattern = "HH:mm")  // Format for the time in the JSON payload
	    private LocalTime timeField;

	    private String suggestions;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public LocalDate getDateField() {
	        return dateField;
	    }

	    public void setDateField(LocalDate dateField) {
	        this.dateField = dateField;
	    }

	    public LocalTime getTimeField() {
	        return timeField;
	    }

	    public void setTimeField(LocalTime timeField) {
	        this.timeField = timeField;
	    }

	    public String getSuggestions() {
	        return suggestions;
	    }

	    public void setSuggestions(String suggestions) {
	        this.suggestions = suggestions;
	    }

	    public Booking(Long id, String name, String email, LocalDate dateField, LocalTime timeField, String suggestions) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.dateField = dateField;
	        this.timeField = timeField;
	        this.suggestions = suggestions;
	    }

	    public Booking() {
	    }

} 	

package com.assignment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;


@Entity
@Data
public class TutorAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tutorId;
    private LocalDateTime lastActive;
    private boolean isAvailable;
	public void setIsAvailable(boolean after) {
		// TODO Auto-generated method stub
		
	}
    
}

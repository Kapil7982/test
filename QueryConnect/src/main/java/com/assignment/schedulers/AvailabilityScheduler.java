package com.assignment.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.assignment.services.TutorAvailabilityService;

@Component
public class AvailabilityScheduler {
    private final TutorAvailabilityService tutorAvailabilityService;

    public AvailabilityScheduler(TutorAvailabilityService tutorAvailabilityService) {
        this.tutorAvailabilityService = tutorAvailabilityService;
    }

    @Scheduled(fixedRate = 1000)
    public void checkTutorAvailability() {
        tutorAvailabilityService.checkTutorAvailability();
    }
}
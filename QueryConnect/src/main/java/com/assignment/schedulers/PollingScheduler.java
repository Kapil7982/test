package com.assignment.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.assignment.services.TutorAvailabilityService;

@Component
public class PollingScheduler {
    private final TutorAvailabilityService tutorAvailabilityService;

    public PollingScheduler(TutorAvailabilityService tutorAvailabilityService) {
        this.tutorAvailabilityService = tutorAvailabilityService;
    }

    @Scheduled(fixedRate = 3000)
    public void updateTutorAvailability() {
        tutorAvailabilityService.updateTutorAvailability();
    }
}

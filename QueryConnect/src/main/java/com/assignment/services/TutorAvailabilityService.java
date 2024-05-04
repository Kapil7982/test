package com.assignment.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.models.Customer;
import com.assignment.models.TutorAvailability;

import com.assignment.repositories.CustomerRepository;
import com.assignment.repositories.TutorAvailabilityRepository;


@Service
public class TutorAvailabilityService {
    private final CustomerRepository customerRepository;
    private final TutorAvailabilityRepository tutorAvailabilityRepository;

    public TutorAvailabilityService(CustomerRepository customerRepository,
                                    TutorAvailabilityRepository tutorAvailabilityRepository) {
        this.customerRepository = customerRepository;
        this.tutorAvailabilityRepository = tutorAvailabilityRepository;
    }

    public void updateTutorAvailability() {
        List<Customer> tutors = customerRepository.findByRole("tutor");
        List<TutorAvailability> availabilityEntries = new ArrayList<>();

        for (Customer tutor : tutors) {
            TutorAvailability availability = new TutorAvailability();
            availability.setTutorId(tutor.getCustId());
            availability.setLastActive(LocalDateTime.now());
            availabilityEntries.add(availability);
        }

        tutorAvailabilityRepository.deleteAll();
        tutorAvailabilityRepository.saveAll(availabilityEntries);
    }

    public void checkTutorAvailability() {
        List<TutorAvailability> availabilityEntries = tutorAvailabilityRepository.findAll();
        LocalDateTime threeSecondsAgo = LocalDateTime.now().minusSeconds(3);

        for (TutorAvailability entry : availabilityEntries) {
            entry.setIsAvailable(entry.getLastActive().isAfter(threeSecondsAgo));
        }

        tutorAvailabilityRepository.saveAll(availabilityEntries);
    }
}

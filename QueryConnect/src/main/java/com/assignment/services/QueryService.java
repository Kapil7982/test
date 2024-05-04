package com.assignment.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.assignment.models.Customer;
import com.assignment.models.Query;
import com.assignment.models.TutorAvailability;
import com.assignment.repositories.CustomerRepository;
import com.assignment.repositories.QueryRepository;
import com.assignment.repositories.TutorAvailabilityRepository;


@Service
public class QueryService {
    private final QueryRepository queryRepository;
    private final CustomerRepository customerRepository;
    private final TutorAvailabilityRepository tutorAvailabilityRepository;

    public QueryService(QueryRepository queryRepository, CustomerRepository customerRepository, TutorAvailabilityRepository tutorAvailabilityRepository) {
        this.queryRepository = queryRepository;
        this.customerRepository = customerRepository;
        this.tutorAvailabilityRepository = tutorAvailabilityRepository;
    }

    public List<Query> getQueryHistoryForStudent(Long studentId) {
        return queryRepository.findByStudentIdOrderByTimestampDesc(studentId);
    }

    public void submitQuery(Long studentId, String subject, String query) {
        Customer student = customerRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Query newQuery = new Query();
        newQuery.setStudentId(studentId);
        newQuery.setSubject(subject);
        newQuery.setQuery(query);
        newQuery.setTimestamp(LocalDateTime.now());
        queryRepository.save(newQuery);

        List<TutorAvailability> availableTutors = tutorAvailabilityRepository.findAll().stream()
                .filter(TutorAvailability::isAvailable)
                .filter(tutorAvailability -> {
                	Customer tutor = customerRepository.findById(tutorAvailability.getTutorId()).orElseThrow(() -> new RuntimeException("Tutor not found"));
                    return tutor.getSubjects().contains(subject) && tutor.getLanguage().equals(student.getLanguage());
                })
                .collect(Collectors.toList());

        // Notify the first available tutor
        if (!availableTutors.isEmpty()) {
            Long tutorId = availableTutors.get(0).getTutorId();
            // Implement logic to notify the tutor
        } else {
            // Implement logic to notify the student that no tutors are available
        }
    }
}


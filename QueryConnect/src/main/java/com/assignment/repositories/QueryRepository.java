package com.assignment.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.assignment.models.Query;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    List<Query> findByStudentIdOrderByTimestampDesc(Long studentId);
}

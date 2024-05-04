package com.assignment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.models.Query;
import com.assignment.models.QueryRequest;
import com.assignment.services.QueryService;

@RestController
@RequestMapping("/api/queries")
public class QueryController {
    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/history/{studentId}")
    public List<Query> getQueryHistoryForStudent(@PathVariable Long studentId) {
        return queryService.getQueryHistoryForStudent(studentId);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuery(@RequestBody QueryRequest queryRequest) {
        try {
            queryService.submitQuery(queryRequest.getStudentId(), queryRequest.getSubject(), queryRequest.getQuery());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
package com.assignment.models;

import lombok.Data;

@Data
public class QueryRequest {
    private Long studentId;
    private String subject;
    private String query;

}

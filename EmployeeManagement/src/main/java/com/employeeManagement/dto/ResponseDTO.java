package com.employeeManagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseDTO {
    private String errorMessage;
    private List<CurrencyDTO> data;
    
    public ResponseDTO() {
    }

    public ResponseDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResponseDTO(List<CurrencyDTO> data) {
        this.data = data;
    }
}

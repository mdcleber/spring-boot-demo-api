package com.microservices.demo.api.dtos.responses;

import java.util.Date;

import lombok.Data;

public @Data class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String reportsTo;    
    private String teamName;
    private Integer role;
    private Date hiredOn;
}
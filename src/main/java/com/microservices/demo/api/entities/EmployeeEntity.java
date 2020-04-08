package com.microservices.demo.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees", schema = "demo") 
@Data
public class EmployeeEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "reports_to")
    private String reportsTo;
    
    @Column(name = "team_name")
    private String teamName;
    private Integer role;

    @Column(name = "hired_on")
    private Date hiredOn;
}
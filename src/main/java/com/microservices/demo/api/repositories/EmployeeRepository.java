package com.microservices.demo.api.repositories;

import java.util.List;

import com.microservices.demo.api.entities.EmployeeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findByFirstName(String FirstName);
    List<EmployeeEntity> findAll();
}
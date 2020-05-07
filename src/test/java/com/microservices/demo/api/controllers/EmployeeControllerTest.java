package com.microservices.demo.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.microservices.demo.api.dtos.responses.EmployeeDto;
import com.microservices.demo.api.entities.EmployeeEntity;
import com.microservices.demo.api.repositories.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    private EmployeeRepository superHeroRepository;

    private EmployeeController controller;

    private ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        controller = new EmployeeController();
        superHeroRepository = mock(EmployeeRepository.class);
        controller.repository = superHeroRepository;

        mapper = new ModelMapper();
        controller.mapper = mapper;
    }
    
    @Test
    public void canRetrieveByIdWhenExists() {

        EmployeeEntity emp = new EmployeeEntity();
        emp.setId(2);
        emp.setFirstName("firstName");
        emp.setLastName("lastName");
        
        when(superHeroRepository.findById(2)).thenReturn(Optional.of(emp));        

        final ResponseEntity<EmployeeDto> response = controller.getById(2);         
         
         assertThat(response).isNotNull();
         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }   
}
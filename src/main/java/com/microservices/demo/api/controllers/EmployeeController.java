package com.microservices.demo.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.microservices.demo.api.dtos.responses.EmployeeDto;
import com.microservices.demo.api.entities.EmployeeEntity;
import com.microservices.demo.api.repositories.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAll() {

        List<EmployeeEntity> employees = repository.findAll();
        List<EmployeeDto> employeesDto = employees.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok().body(employeesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id) {

        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent())
            return ResponseEntity.ok().body(convertToDto(employee.get()));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeDto>> getByFirstName(@PathVariable String name) {

        List<EmployeeEntity> employees = repository.findByFirstName(name);
        if (employees == null || employees.isEmpty())
            return ResponseEntity.notFound().build();

        List<EmployeeDto> employeesDto = employees.stream().map(this::convertToDto).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(employeesDto);
    }

    
    @PostMapping("/")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {

        EmployeeEntity employeeEntity = repository.save(convertToEntity(employeeDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(employeeEntity));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<EmployeeDto>> bulkCreate(@RequestBody List<EmployeeDto> employeesDto) {

        List<EmployeeEntity> employees = employeesDto.stream().map(this::convertToEntity).collect(Collectors.toList());
        
        repository.saveAll(employees);

        List<EmployeeDto> employessResult = employees.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(employessResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {

        if(repository.existsById(id))
        {
            EmployeeEntity employee = convertToEntity(employeeDto);
            repository.save(employee);

            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    private EmployeeDto convertToDto(EmployeeEntity employee) {

        EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);

        return employeeDto;
    }

    private EmployeeEntity convertToEntity(EmployeeDto employeeDto) {

        EmployeeEntity employee = mapper.map(employeeDto, EmployeeEntity.class);

        return employee;
    }
}
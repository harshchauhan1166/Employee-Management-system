package com.springBoot.SpringBootPractice.employee.advices.controller;


import com.springBoot.SpringBootPractice.employee.DTO.ResponseEmployeeDto;
import com.springBoot.SpringBootPractice.employee.dto.EmployeeDto;
import com.springBoot.SpringBootPractice.employee.entity.Employee;
import com.springBoot.SpringBootPractice.employee.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/employee")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping("/add")
    @ApiOperation("Add only one employee")
    public ResponseEntity<ResponseEmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        ResponseEmployeeDto savedEmployee = service.addEmployee(employeeDto);
        if (savedEmployee != null) {
            return new ResponseEntity(savedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity("duplicate data entry", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addMultiple")
    @ApiOperation("Add multiple Employee")
    public ResponseEntity<List<ResponseEmployeeDto>> addEmployees(@RequestBody List<EmployeeDto> employees) {
        List<ResponseEmployeeDto> savedEmployees = service.saveEmployees(employees);
        if (savedEmployees != null && savedEmployees.size() > 0) {
            return new ResponseEntity(savedEmployees, HttpStatus.OK);
        } else {
            return new ResponseEntity("Unable to save", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    @ApiOperation("Get ALL Employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return new ResponseEntity(service.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Employee By Id")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable int id) {
        if (id != 0) {
            return new ResponseEntity(service.getEmployeeById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity("id not exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    @ApiOperation("Update Employees")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestBody EmployeeDto employee,
            @RequestParam("id") int id) {
        EmployeeDto updateEmployee = service.updateEmployee(employee, id);
        if (updateEmployee != null) {
            return new ResponseEntity(updateEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity("data does not exits", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete Employee")

    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable int id) {
        ResponseEmployeeDto getEmployee = service.getEmployeeById(id);
        if (getEmployee != null) {
            return new ResponseEntity(service.deleteEmployee(id), HttpStatus.OK);
        } else {
            return new ResponseEntity(" data not exists", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/page")
    @ApiOperation("Get Employee Information In Page")
    public ResponseEntity<Page<EmployeeDto>> employeePagination(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort", defaultValue = "0") int sort,
            @RequestParam(value = "sortBy", defaultValue = "firstName") String sortBy) {
        return new ResponseEntity(service.getEmployeePagination(pageNo, pageSize, sort, sortBy), HttpStatus.OK);
    }


    @GetMapping("/getPage")
    public ResponseEntity<Page<Employee>> employeePagination(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(service.getEmployeePagination(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/updateIsDeleted")
    @ApiOperation("Update Employee Is Deleted")
    public ResponseEntity<Employee> updateEmployeeIsDeleted(@RequestParam("id") int id, @RequestParam("value") boolean value) {
        Employee updateEmployeeIsDeleted = service.updateEmployee(id, value);
        return new ResponseEntity(updateEmployeeIsDeleted, HttpStatus.OK);

    }
    @GetMapping("/getALL")
    public  List<Employee> getAll(){
        return service.getAll();
    }

    @PostMapping("/sveAll")
    public Employee addAll(@RequestBody Employee employee){
        return service.addAll(employee);
    }

    @GetMapping("/query")
    public Employee getById(@RequestParam int id){
        return service.getById(id);
    }
}





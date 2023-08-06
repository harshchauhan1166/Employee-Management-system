package com.springBoot.SpringBootPractice.department.controller;

import com.springBoot.SpringBootPractice.department.dto.DepartmentDto;
import com.springBoot.SpringBootPractice.department.entity.Department;
import com.springBoot.SpringBootPractice.department.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/department")
@RestController
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;



    @PostMapping("/add")
    @ApiOperation("Add Department")

    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = (DepartmentDto) departmentService.addDepartment(departmentDto);
        if (savedDepartment != null) {
            return new ResponseEntity(savedDepartment, HttpStatus.OK);
        } else {
            return new ResponseEntity("enter required data", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addMultiple")
    @ApiOperation("Add Multiple Department")

    public ResponseEntity<List<DepartmentDto>> addDepartments(@RequestBody List<DepartmentDto> departments) {
        List<DepartmentDto> saveDepartments = departmentService.saveDepartments(departments);
        if (saveDepartments != null && saveDepartments.size() > 0) {
            return new ResponseEntity(saveDepartments, HttpStatus.OK);
        } else {
            return new ResponseEntity("Unable to save", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    @ApiOperation("Get Department")
    public ResponseEntity<List<DepartmentDto>> findAllDepartments() {
//         List<Employee>list=service.getEmployees();

        return new ResponseEntity(departmentService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get Department By Id")

    public ResponseEntity<DepartmentDto> findDepartmentById(@PathVariable int id) {
        if (id != 0) {
            return new ResponseEntity(departmentService.getDepartmentById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity("id not exists", HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    @ApiOperation("Update Department")

    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        Department updateDepartment = departmentService.updateDepartment(department);
        if (updateDepartment != null) {
            return new ResponseEntity(updateDepartment(department), HttpStatus.OK);
        } else {
            return new ResponseEntity("data does not exits", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete Department By Id")

    public ResponseEntity<Department> deleteDepartment(@PathVariable int id) {
        return new ResponseEntity(departmentService.deleteDepartment(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    @ApiOperation("Get Department Pagination")

    public ResponseEntity<List<DepartmentDto>> departmentPagination(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort", defaultValue = "0") int sort,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy
    ) {
        return new ResponseEntity(departmentService.getDepartmentPagination(pageNo, pageSize, sort, sortBy), HttpStatus.OK);
    }



    @PutMapping("/dIsDeleted")
    @ApiOperation("Update Department IsDeleted")

    public ResponseEntity<Department> updateDepartmentIsDeleted(@RequestParam("id") int id, @RequestParam("value") boolean value) {
        Department updateDepartmentIsDeleted = departmentService.updateDepartmentIsDeleted(id, value);
        return new ResponseEntity(updateDepartmentIsDeleted, HttpStatus.OK);
    }


    @GetMapping("/getEmployeeByDepartment")
    public void getEmployeeByDepartment(int departmentId){

        departmentService.getEmployee(departmentId);

    }
}

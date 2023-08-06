//package com.springBoot.SpringBootPractice.department.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.springBoot.SpringBootPractice.employee.entity.Employee;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "DEPARTMENT_TBL")
//public class Department extends Auditable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String departmentName;
//    private Boolean isDeleted;
//
//    @ManyToMany(mappedBy = "departments",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Employee> employees;
//
//
//}
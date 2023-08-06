package com.springBoot.SpringBootPractice.department.repository;

import com.springBoot.SpringBootPractice.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByDepartmentName(String departmentName);
}

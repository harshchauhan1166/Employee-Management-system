package com.springBoot.SpringBootPractice.department.dto;

import com.springBoot.SpringBootPractice.employee.dto.EmployeeDto;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    private String departmentName;
}

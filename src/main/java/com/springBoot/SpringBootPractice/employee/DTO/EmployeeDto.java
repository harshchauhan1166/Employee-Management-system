package com.springBoot.SpringBootPractice.employee.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springBoot.SpringBootPractice.department.dto.DepartmentDto;
import com.springBoot.SpringBootPractice.department.entity.Department;
import com.springBoot.SpringBootPractice.employee.entity.Employee;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDto {
    @NotNull(message = "firstName must not be empty")
    private String firstName;
    @NotNull(message = "lastName must not be empty")
    private String lastName;
    @NotNull(message = "address must not be empty")
    private String address;
    @NotNull(message = "gender must not be empty")
    private String gender;
    @Email(message = "Invalid email entered")
    private String email;

    private List<Department> departments;


    public EmployeeDto(String firstName, String lastName, String address, String gender, String email) {
    }

}

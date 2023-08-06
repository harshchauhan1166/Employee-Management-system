package com.springBoot.SpringBootPractice.employee.DTO;


import com.springBoot.SpringBootPractice.department.entity.Department;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseEmployeeDto {
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    private List<Department> departments;


}

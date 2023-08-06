package com.springBoot.SpringBootPractice.employee.entity;

import com.springBoot.SpringBootPractice.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_TBL")
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private double salary;
    private String gender;
    private String email;
    private Boolean isDeleted;

    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(name = "employee_department",
            joinColumns = {
                    @JoinColumn(name = "employee_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "department_id")
            })
    private List<Department> departments;

}




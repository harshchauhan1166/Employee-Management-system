package com.springBoot.SpringBootPractice.employee.repository;


import com.springBoot.SpringBootPractice.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


@Component
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    String deleteById(int id);
    Employee findByEmail(String firstName);

    @Query("Select e From Employee e where e.id=:id")
    Employee getDataQuery(int id);

}



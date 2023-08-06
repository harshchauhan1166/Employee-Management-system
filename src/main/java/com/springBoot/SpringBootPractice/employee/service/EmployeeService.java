package com.springBoot.SpringBootPractice.employee.service;

import com.springBoot.SpringBootPractice.employee.DTO.ResponseDto;
import com.springBoot.SpringBootPractice.employee.DTO.ResponseEmployeeDto;
import com.springBoot.SpringBootPractice.employee.dto.EmployeeDto;
import com.springBoot.SpringBootPractice.employee.entity.Employee;
import com.springBoot.SpringBootPractice.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService extends Exception {
    @Autowired
    private EmployeeRepository repository;


    public ResponseEmployeeDto addEmployee(EmployeeDto employeeDto) {
        log.info("Duplicate check");
        boolean isDuplicate = checkDuplicateEmployee(employeeDto);
        if (!isDuplicate) {
            Employee e1 = getEmployee(employeeDto);
            repository.save(e1);
            ResponseEmployeeDto responseEmployeeDto =
            ResponseEmployeeDto.builder()
                    .firstName(employeeDto.getFirstName())
                    .lastName(employeeDto.getLastName())
                    .address(employeeDto.getAddress())
                    .gender(employeeDto.getGender())
                    .departments(employeeDto.getDepartments())
                    .build();
//repository.save(e1);
            return responseEmployeeDto;
        }

        return null;
    }

    private static Employee getEmployee(EmployeeDto employeeDto) {
        Employee e1 = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .address(employeeDto.getAddress())
                .salary(15000.0)
                .isDeleted(false)
                .gender(employeeDto.getGender())
                .email(employeeDto.getEmail())
                .departments(employeeDto.getDepartments())
                .build();
        return e1;
    }


    private boolean validateEmployee(EmployeeDto employee) {
        if (employee.getFirstName() == null || employee.getFirstName().equals("")) {
            return false;
        }
        if (employee.getLastName() == null || employee.getLastName().equals("")) {
            return false;
        }
        if (employee.getAddress() == null || employee.getAddress().equals("")) {
            return false;
        }
        if (employee.getGender() == null || employee.getGender().equals("")) {
            return false;
        }
        if (employee.getEmail() == null || employee.getEmail().equals("")) {
            return false;
        } else {
            return true;
        }
    }


    private boolean checkDuplicateEmployee(EmployeeDto employeeDto) {
        Employee existingEmployee = repository.findByEmail(employeeDto.getEmail());
        return existingEmployee != null && employeeDto.getEmail().equals(existingEmployee.getEmail());
    }

    public List<ResponseEmployeeDto> saveEmployees(List<EmployeeDto> employeesDtoList) {
        List<Employee> employeeList = new ArrayList<>();
        employeesDtoList.forEach((employeeDto) -> {
            employeeList.add(Employee.builder()
                    .firstName(employeeDto.getFirstName())
                    .lastName(employeeDto.getLastName())
                    .address(employeeDto.getAddress())
                    .salary(15000)
                    .gender(employeeDto.getGender())
                    .email(employeeDto.getEmail())
                    .isDeleted(false)
                    .departments(employeeDto.getDepartments())
                    .build());
        });
        repository.saveAll(employeeList);
        List<ResponseEmployeeDto> responseEmployeeDtoList = new ArrayList<>();
        responseEmployeeDtoList.forEach((employee) -> {
            responseEmployeeDtoList.add(new ResponseEmployeeDto(employee.getFirstName(),
                    employee.getLastName(),
                    employee.getAddress(),
                    employee.getGender(),
                    employee.getDepartments()));
        });
        return responseEmployeeDtoList;
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = repository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employees.forEach((employee) -> employeeDtoList.add(

                EmployeeDto.builder()
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .address(employee.getAddress())
                        .gender(employee.getGender())
                        .email(employee.getEmail())
                        .departments(employee.getDepartments())
                        .build()
        ));

        return employeeDtoList;
    }

    private static Employee buildEmployeeDto(EmployeeDto employeeDto){
        Employee employee=Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .address(employeeDto.getAddress())
                .gender(employeeDto.getGender())
                .email(employeeDto.getEmail())
                .departments(employeeDto.getDepartments())
                .build();
        return employee;
    }

    public ResponseEmployeeDto getEmployeeById(int id) {

        Employee employeeGetById = repository.findById(id).get();
        if (employeeGetById !=null) {
            ResponseEmployeeDto employeeDto = new ResponseEmployeeDto();
            BeanUtils.copyProperties(employeeGetById, employeeDto);

            return employeeDto;
        }
       else return null;
    }

    public ResponseDto deleteEmployee(int id) {
        Optional<Employee> getEmployeeByID = repository.findById(id);
        if (getEmployeeByID.isPresent()) {
            repository.deleteById(id);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("Employee removed !!" + id);
            return responseDto;
        }
        return null;
    }

    @NotNull
    public EmployeeDto updateEmployee(EmployeeDto employee, int id) {
        Employee existingEmployee = repository.findById(id).get();
        EmployeeDto employeeDto = new EmployeeDto();
        if (existingEmployee != null) {
            if (employee.getFirstName().equals("") || employee.getFirstName() != null) {
                existingEmployee.setFirstName(employee.getFirstName());
            }
            if (existingEmployee.getLastName().equals("") || employee.getLastName() != null) {
                existingEmployee.setLastName(employee.getLastName());
            }

            if (existingEmployee.getGender().equals("") || employee.getGender() != null) {
                existingEmployee.setGender(employee.getGender());
            }
            if (existingEmployee.getEmail().equals("") || employee.getEmail() != null) {
                existingEmployee.setEmail(employee.getEmail());
            }
            if (existingEmployee.getAddress().equals("") || employee.getAddress() != null) {
                existingEmployee.setAddress(employee.getAddress());
            }

            repository.save(existingEmployee);
            BeanUtils.copyProperties(existingEmployee, employeeDto);
            return employeeDto;

        }
        return null;
    }

    @NotNull
    public Page<EmployeeDto> getEmployeePagination(int pageNo, int pageSize, int sort, String sortBy) {
        try {
            Pageable pageable = getPageable(pageNo, pageSize, sort, sortBy);


            Page<EmployeeDto> employees = repository.findAll(pageable)
                    .map(EmployeeService::employeeConverter);

            return employees;
        } catch (Exception e) {
        }
        return null;
    }

    private static EmployeeDto employeeConverter(Employee e) {
        return new EmployeeDto(e.getFirstName(),
                e.getLastName(),
                e.getAddress(),
                e.getGender(),
                e.getEmail());
    }
    private static Pageable getPageable(int pageNo, int pageSize, int sort, String sortBy) {
        Sort sorting = null;
        if (sort == 0) {
            sorting = Sort.by(Sort.Direction.ASC, sortBy);
        } else if (sort == 1) {
            sorting = Sort.by(Sort.Direction.DESC, sortBy);
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sorting);
        return pageable;
    }

    public Page<Employee> getEmployeePagination(int pageNo, int pageSize) {

        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return repository.findAll(pageable);
    }

    @NotNull
    public Employee updateEmployee(int id, boolean value) {
        Employee existingEmployee = repository.findById(id).get();
        if (existingEmployee != null) {

            if (existingEmployee.getIsDeleted() != null || existingEmployee.getIsDeleted().equals("")) {
                existingEmployee.setIsDeleted(value);
            }
            return repository.save(existingEmployee);
        }
        return null;
    }

    public List<Employee> getAll() {
        return repository.findAll();

    }

    public Employee addAll(Employee employee) {
        return repository.save(employee);
    }


    public Employee getById(int id){
        return repository.getDataQuery(id);
    }
}


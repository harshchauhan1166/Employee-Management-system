package com.springBoot.SpringBootPractice.department.service;

import com.springBoot.SpringBootPractice.department.dto.DepartmentDto;
import com.springBoot.SpringBootPractice.department.entity.Department;
import com.springBoot.SpringBootPractice.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        boolean isValid = validateDepartment(departmentDto);
        if (isValid) {
            Department d1 = Department.builder()
                    .departmentName(departmentDto.getDepartmentName())
                    .isDeleted(false)
                    .build();
            departmentRepository.save(d1);
            return departmentDto;
        }
        return null;
    }

    private boolean validateDepartment(DepartmentDto department) {
        if (department.getDepartmentName() == null || department.getDepartmentName().equals("")) {
            return false;
        } else {
            return true;
        }
    }


    public List<DepartmentDto> saveDepartments(List<DepartmentDto> departmentDtoList) {
        List<Department> departmentList = new ArrayList<>();
        departmentDtoList.forEach((departmentDto -> {
            departmentList.add(Department.builder()
                    .departmentName(departmentDto.getDepartmentName())
                    .isDeleted(false)
                    .build());
        }));
        departmentRepository.saveAll(departmentList);
        return departmentDtoList;
    }

    public List<DepartmentDto> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departments.forEach((department) -> departmentDtoList.add(new DepartmentDto
                (department.getDepartmentName()
                ))
        );
        return departmentDtoList;
    }

    public DepartmentDto getDepartmentById(int id) {
        Optional<Department> departmentGetById = departmentRepository.findById(id);
        if (departmentGetById.isPresent()) {
            DepartmentDto departmentDto = new DepartmentDto(departmentGetById.get().getDepartmentName());
            return departmentDto;

        }
        return null;
    }

    public String deleteDepartment(int Id) {
        try {
            departmentRepository.deleteById(Id);
            return "Department removed !!" + Id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public Department updateDepartment(Department department) {
        Department existingDepartment = departmentRepository.findById(department.getId()).get();
        if (existingDepartment != null) {
            if (department.getDepartmentName().equals("") || department.getDepartmentName() != null) {
                existingDepartment.setDepartmentName(department.getDepartmentName());
            }
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }

    public Page<DepartmentDto> getDepartmentPagination(int pageNo, int pageSize, int sort, String sortBy) {
        try {
            Pageable pageable = getPageable(pageNo, pageSize, sort, sortBy);

            Page<DepartmentDto> departments = departmentRepository.findAll(pageable)
                    .map(DepartmentService::departmentConverter);

            return departments;
        } catch (Exception e) {
        }
        return null;
    }

    private static DepartmentDto departmentConverter(Department d) {
        return new DepartmentDto(d.getDepartmentName());
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

    public Department updateDepartmentIsDeleted(int id, boolean value) {
        Department existingDepartment = departmentRepository.findById(id).get();
        if (existingDepartment != null) {
            if (existingDepartment.getIsDeleted() != null || existingDepartment.getIsDeleted().equals("")) {
                existingDepartment.setIsDeleted(value);
            }
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }

    public void getEmployee(int departmentId) {
        Department d = departmentRepository.findById(departmentId).get();
        System.out.println(d.getEmployees());
    }
}

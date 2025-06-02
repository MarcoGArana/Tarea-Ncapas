package com.ldar01.demoemployees.service.impl;

import com.ldar01.demoemployees.dto.request.vacation.VacationRequest;
import com.ldar01.demoemployees.dto.request.vacation.VacationUpdateRequest;
import com.ldar01.demoemployees.dto.response.department.DepartmentResponse;
import com.ldar01.demoemployees.dto.response.employee.EmployeeResponse;
import com.ldar01.demoemployees.dto.response.vacation.VacationResponse;
import com.ldar01.demoemployees.entities.Employee;
import com.ldar01.demoemployees.exception.EmployeeNotFoundException;
import com.ldar01.demoemployees.exception.VacationNotFoundException;
import com.ldar01.demoemployees.repository.VacationRepository;
import com.ldar01.demoemployees.service.DepartmentService;
import com.ldar01.demoemployees.service.EmployeeService;
import com.ldar01.demoemployees.service.VacationService;
import com.ldar01.demoemployees.utils.mappers.EmployeeMapper;
import com.ldar01.demoemployees.utils.mappers.VacationMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {
    private final VacationRepository vacationRepository;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public VacationServiceImpl(VacationRepository vacationRepository, EmployeeService employeeService, DepartmentService departmentService) {
        this.vacationRepository = vacationRepository;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }


    @Override
    public List<VacationResponse> findAll() {
        return VacationMapper.toDTOList(vacationRepository.findAll());
    }

    @Override
    public VacationResponse findById(int id) {
        return VacationMapper.toDTO(vacationRepository.findById(id)
                .orElseThrow(() -> new VacationNotFoundException("Vacation not found")));
    }

    @Override
    public List<VacationResponse> findByEmployeeId(int employeeId) {
        return VacationMapper.toDTOList(vacationRepository.findByEmployee_Id(employeeId));
    }

    @Override
    @Transactional
    public VacationResponse save(VacationRequest vacation) {
        EmployeeResponse employee = employeeService.findById(vacation.getEmployeeId());
        DepartmentResponse department = departmentService.findByName(employee.getDepartment());
        return VacationMapper.toDTO(
                vacationRepository.save(
                        VacationMapper.toEntityCreate(
                                vacation,
                                EmployeeMapper.toEntity(employee, department)
                        )
                )
        );
    }

    @Override
    @Transactional
    public VacationResponse update(VacationUpdateRequest vacation) {
        EmployeeResponse employee = employeeService.findById(vacation.getEmployeeId());
        DepartmentResponse department = departmentService.findByName(employee.getDepartment());
        return VacationMapper.toDTO(
                vacationRepository.save(
                        VacationMapper.toEntityUpdate(
                                vacation,
                                EmployeeMapper.toEntity(employee, department)
                        )
                )
        );
    }

    @Override
    public void delete(int id) {
        vacationRepository.deleteById(id);
    }
}

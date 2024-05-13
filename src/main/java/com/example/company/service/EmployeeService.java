package com.example.company.service;

import com.example.company.model.EmployeeDTO;
import com.example.company.model.EmployeeDtoInterface;

import java.util.List;

public interface EmployeeService {

    public void employeeInsert(EmployeeDTO employeeDTO);

    public List<EmployeeDtoInterface> empOut();
}

package com.example.company.service;

import com.example.company.model.EmployeeDTO;
import com.example.company.model.EmployeeDtoInterface;
import com.example.company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void employeeInsert(EmployeeDTO employeeDTO) {
        employeeDTO.setPw(bCryptPasswordEncoder.encode(employeeDTO.getPw()));//패스워드를 가져와서 암호화 시켜서 dto에 다시set해줌
        employeeRepository.save(employeeDTO.toEntity());
    }

    @Override
    public List<EmployeeDtoInterface> empOut() {
        return employeeRepository.empOut();
    }

}

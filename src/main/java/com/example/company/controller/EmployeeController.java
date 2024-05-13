package com.example.company.controller;

import com.example.company.model.EmployeeDTO;
import com.example.company.model.EmployeeDtoInterface;
import com.example.company.repository.EmployeeRepository;
import com.example.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/empInsert")
    public String employeeInsertPage(Model model){
        model.addAttribute("employeeDTO",new EmployeeDTO());
        return "employee/employeeInsert";
    }

    @PostMapping("/empInsert")
    public String employeeInsert(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
        employeeService.employeeInsert(employeeDTO);
        return "redirect:/empInsert";
    }

    @GetMapping("/empOut")
    public String employeeOutPage(Model model){
        List<EmployeeDtoInterface> list = employeeService.empOut();
        model.addAttribute("list",list);
        return "employee/employeeOut";
    }
}

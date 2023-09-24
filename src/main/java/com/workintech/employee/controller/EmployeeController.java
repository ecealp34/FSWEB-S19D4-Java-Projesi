package com.workintech.employee.controller;

import com.workintech.employee.entity.Employee;
import com.workintech.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @GetMapping("/byEmail/{email}")
    public Employee findByEmail(@PathVariable String email) {
        return employeeService.findByEmail(email);
    }

    @GetMapping("/byOrder")
    public List<Employee> findByOrder() {
        return employeeService.findByOrder();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    @PostMapping("/bySalary/{salary}")
    public List<Employee> findBySalary(@PathVariable double salary) {
        return employeeService.findBySalary(salary);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable int id) {
        return employeeService.delete(id);
    }
}

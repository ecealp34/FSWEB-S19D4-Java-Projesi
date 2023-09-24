package com.workintech.employee.service;

import com.workintech.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee delete(int id);
    Employee findByEmail(String email);
    List<Employee> findBySalary(double salary);
    List<Employee> findByOrder();

}
